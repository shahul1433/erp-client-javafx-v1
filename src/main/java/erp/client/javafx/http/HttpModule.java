package erp.client.javafx.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.login.AuthenticationResponse;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.JacksonService;

public class HttpModule {

	private static ObjectMapper objectMapper;
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String APPLICATION_JSON = "application/json";
	private static final String IS_REFRESH_TOKEN = "isRefreshToken";

	static {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		objectMapper.registerModule(new JavaTimeModule());
		
	}

	public static <T> ResponseEntity<T> getRequest(String url, TypeReference<T> classOfT)
			throws ClientProtocolException, IOException, UnsupportedOperationException, FormValidationException {
	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		
		RefreshTokenAttributes refreshTokenAttributes = new RefreshTokenAttributes(1);
		
		CloseableHttpResponse response = null;
		try {
			do {
				// Headers
				if(refreshTokenAttributes.isTokenRefreshed()) {
					get.removeHeaders(AUTHORIZATION);
				}
				get.addHeader(AUTHORIZATION, AppSession.getAuthorization());
				
				response = httpClient.execute(get);
				
				refreshTokenAttributes.setTokenRefreshed(false);
				
				validateResponse(response, refreshTokenAttributes);
				
			} while (refreshTokenAttributes.isTokenRefreshed() && (refreshTokenAttributes.getCurrentAttempt() <= refreshTokenAttributes.getMaximumAttempt()));
			
			T object = JacksonService.jsonToObject(classOfT, getResponse(response));
			EntityUtils.consume(response.getEntity());
			return new ResponseEntity<T>(object, response);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	public static <T> ResponseEntity<T> postRequest(String url, Object payload, TypeReference<T> classOfT)
			throws ClientProtocolException, IOException, UnsupportedOperationException, FormValidationException, Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		RefreshTokenAttributes refreshTokenAttributes = new RefreshTokenAttributes(1);

		CloseableHttpResponse response = null;
		try {
			do {
				// Headers
				if(refreshTokenAttributes.isTokenRefreshed()) {
					post.removeHeaders(AUTHORIZATION);
				}
				post.addHeader(AUTHORIZATION, AppSession.getAuthorization());
				
				String entity = payload instanceof String ? payload.toString() : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
				
				StringEntity input = new StringEntity(entity);
				input.setContentType(APPLICATION_JSON);
				post.setEntity(input);
				
				response = httpClient.execute(post);
				
				refreshTokenAttributes.setTokenRefreshed(false);
				
				validateResponse(response, refreshTokenAttributes);
				
			} while (refreshTokenAttributes.isTokenRefreshed() && (refreshTokenAttributes.getCurrentAttempt() <= refreshTokenAttributes.getMaximumAttempt()));

			T object = JacksonService.jsonToObject(classOfT, getResponse(response));
			EntityUtils.consume(response.getEntity());
			return new ResponseEntity<T>(object, response);
			
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	
	private static boolean refreshToken() throws FormValidationException, IOException {
		
		boolean isTokenRefreshed = false;
		
		String refreshTokenUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.REFRESH_TOKEN_URL;
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(refreshTokenUrl);
		
		// Headers
		get.addHeader(IS_REFRESH_TOKEN, "true");
		get.addHeader(AUTHORIZATION, AppSession.getAuthorization());
		
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			
			if(response == null) {
				throw new FormValidationException("Something went wrong while connecting to the server");
			}
			
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new FormValidationException("Invalid credentials");
			}
			
			AuthenticationResponse tokenResponse = JacksonService.jsonToObject(new TypeReference<AuthenticationResponse>() {}, JacksonService.getResponse(response));
			AppSession.setAuthorization(tokenResponse.getToken());
			
			isTokenRefreshed = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FormValidationException(e.getMessage());
		} finally {
			if(response != null) {
				response.close();
			}
		}
		return isTokenRefreshed;
	}
	
	private static void validateResponse(CloseableHttpResponse response, RefreshTokenAttributes refreshTokenAttributes) throws FormValidationException,
			JsonMappingException, JsonProcessingException, UnsupportedOperationException, IOException {
		
		var statusCode = response.getStatusLine().getStatusCode();

		if (statusCode < 200 || statusCode > 299) {
			// Handle error based on code
			if (statusCode == HttpStatus.SC_FORBIDDEN) { // 403 - Forbidden
				throw new FormValidationException(getResponse(response));
			} else if(statusCode == HttpStatus.SC_NOT_ACCEPTABLE) { // 406 - Not Acceptable - JWT Token Expired.
				refreshTokenAttributes.setCurrentAttempt(refreshTokenAttributes.getCurrentAttempt() + 1);
				refreshTokenAttributes.setTokenRefreshed(refreshToken());
			} else if (statusCode == HttpStatus.SC_BAD_REQUEST) { // 400 - Bad Request
				List<String> responseList = objectMapper.readValue(getResponse(response),new TypeReference<List<String>>() {});
				StringBuilder sb = new StringBuilder();
				responseList.forEach(e -> sb.append(e).append("\n"));
				throw new FormValidationException(sb.toString());
			} else if (statusCode == HttpStatus.SC_NOT_FOUND) { // 404 - Not Found
				throw new FormValidationException(getResponse(response));
			} else if (statusCode == HttpStatus.SC_CONFLICT) { // 409 - Conflict
				throw new FormValidationException(getResponse(response));
			} else {
				throw new FormValidationException("Unknown status code : " + statusCode
						+ "\n Sorry currently there is no handler to handle this response \n Please contact administration for furthur support.");
			}
		}
	}

	private static String getResponse(CloseableHttpResponse response)
			throws UnsupportedOperationException, IOException {
		var builder = new StringBuilder();
		var reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line;
		while ((line = reader.readLine()) != null)
			builder.append(line);
		return builder.toString();
	}
}
