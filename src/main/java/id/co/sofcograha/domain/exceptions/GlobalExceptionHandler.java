package id.co.sofcograha.domain.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import id.co.sofcograha.domain.responses.HttpCustomResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public HttpCustomResponse handleException(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		Map<String, String> exception = new HashMap<String, String>();
		exception.put("code", "Terjadi kesalahan.");
		exception.put("description", e.getLocalizedMessage());
		return new HttpCustomResponse(null, exception);
	}

}
