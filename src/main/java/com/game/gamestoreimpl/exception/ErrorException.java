package com.game.gamestoreimpl.exception;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public ErrorException(String message) {
		super(message);
	}

	ErrorException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
