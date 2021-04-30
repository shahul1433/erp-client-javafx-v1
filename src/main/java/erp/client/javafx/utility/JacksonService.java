package erp.client.javafx.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import erp.client.javafx.exception.FormValidationException;

public class JacksonService {

	private static final Logger logger = LogManager.getLogger(JacksonService.class);
			
	public static <T> T jsonToObject(final TypeReference<T> type, final String jsonPacket) throws FormValidationException{
		T data = null;
		try {
			data = new ObjectMapper()
					.registerModule(new ParameterNamesModule())
					.registerModule(new Jdk8Module())
					.registerModule(new JavaTimeModule())
					.readValue(jsonPacket, type);
			
		} catch (Exception e) {
			String error = "Jackson parse error : "+e.getMessage()+" , Cause: "+e.getCause();
			logger.error(error);
			e.printStackTrace();
			throw new FormValidationException(error);
		}
		return data;
	}
	
	public static String getResponse(CloseableHttpResponse response) throws UnsupportedOperationException, IOException {
		var builder = new StringBuilder();
		var reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line;
		while((line = reader.readLine()) != null)
			builder.append(line);
		return builder.toString();
	}
}