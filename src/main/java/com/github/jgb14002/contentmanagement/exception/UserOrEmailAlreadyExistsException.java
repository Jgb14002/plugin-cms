package com.github.jgb14002.contentmanagement.exception;

public class UserOrEmailAlreadyExistsException extends RuntimeException
{
	public UserOrEmailAlreadyExistsException(String message)
	{
		super(message);
	}
}
