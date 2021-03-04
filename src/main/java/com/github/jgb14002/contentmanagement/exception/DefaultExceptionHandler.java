package com.github.jgb14002.contentmanagement.exception;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler
{
	private ResponseEntity<ErrorDetails> createResponse(HttpStatus status, Exception ex, HttpServletRequest req)
	{
		final var details = new ErrorDetails(LocalDateTime.now(), req.getMethod(), req.getRequestURI(), status.value(), ex.getMessage());
		return ResponseEntity.status(status).body(details);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req)
	{
		return createResponse(HttpStatus.NOT_FOUND, ex, req);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorDetails> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest req)
	{
		return createResponse(HttpStatus.METHOD_NOT_ALLOWED, ex, req);
	}

	@ExceptionHandler(UserOrEmailAlreadyExistsException.class)
	protected ResponseEntity<ErrorDetails> userOrEmailAlreadyExistsException(UserOrEmailAlreadyExistsException ex, HttpServletRequest req)
	{
		return createResponse(HttpStatus.CONFLICT, ex, req);
	}

	@ExceptionHandler(InvalidTokenException.class)
	protected ResponseEntity<ErrorDetails> invalidTokenException(InvalidTokenException ex, HttpServletRequest req)
	{
		return createResponse(HttpStatus.UNAUTHORIZED, ex, req);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ErrorDetails> constraintViolationException(ConstraintViolationException ex, HttpServletRequest req)
	{
		// Might want to collect all violations instead of one at a time
		var firstViolation = ex.getConstraintViolations().iterator().next();
		var status = HttpStatus.BAD_REQUEST;
		var details = new ErrorDetails(LocalDateTime.now(), req.getMethod(), req.getRequestURI(), status.value(), firstViolation.getMessage());

		return ResponseEntity.status(status).body(details);
	}

	/**
	 * Fallback exception handler to catch anything not explicitly handled or expected.
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorDetails> fallbackExceptionHandler(Exception ex, HttpServletRequest req)
	{
		return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, req);
	}
}
