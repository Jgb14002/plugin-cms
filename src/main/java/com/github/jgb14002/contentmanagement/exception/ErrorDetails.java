package com.github.jgb14002.contentmanagement.exception;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorDetails
{
	private final LocalDateTime timestamp;
	private final String method;
	private final String path;
	private final int code;
	private final String message;
}
