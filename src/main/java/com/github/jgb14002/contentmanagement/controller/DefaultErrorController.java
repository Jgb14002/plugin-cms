package com.github.jgb14002.contentmanagement.controller;

import com.github.jgb14002.contentmanagement.exception.ErrorDetails;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultErrorController implements ErrorController
{
	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	ResponseEntity<ErrorDetails> defaultErrorResponse(HttpServletRequest req)
	{
		HttpStatus status = HttpStatus.NOT_FOUND;
		var originalURI = (String) req.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH);
		var details = new ErrorDetails(LocalDateTime.now(), req.getMethod(), originalURI, status.value(), "Resource not found");
		return ResponseEntity.status(status).body(details);
	}

	@Override
	public String getErrorPath()
	{
		return PATH;
	}
}
