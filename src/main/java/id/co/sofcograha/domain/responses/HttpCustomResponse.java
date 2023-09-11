package id.co.sofcograha.domain.responses;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_EMPTY)
@Setter
@Getter
public class HttpCustomResponse {
	private Object data;
	private Map<String, String> message;

	public HttpCustomResponse(Object data) {
		super();
		this.data = data;
	}

	public HttpCustomResponse(Object data, Map<String, String> message) {
		super();
		this.data = data;
		this.message = message;
	}
}
