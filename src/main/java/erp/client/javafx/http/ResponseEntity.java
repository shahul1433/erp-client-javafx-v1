package erp.client.javafx.http;

import org.apache.http.HttpResponse;

public class ResponseEntity<T> {

	private T entity;
	private HttpResponse httpResponse;
	
	public ResponseEntity(T obj, HttpResponse httpResponse) {
		this.entity = obj;
		this.httpResponse = httpResponse;
	}

	/**
	 * @return the obj
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * @param obj the obj to set
	 */
	public void setEntity(T obj) {
		this.entity = obj;
	}

	/**
	 * @return the httpResponse
	 */
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	/**
	 * @param httpResponse the httpResponse to set
	 */
	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseEntity [obj=").append(entity).append(", httpResponse=").append(httpResponse).append("]");
		return builder.toString();
	}

	public String getMessage() {
		return getHttpResponse().getFirstHeader("message").getValue();
	}
	
}
