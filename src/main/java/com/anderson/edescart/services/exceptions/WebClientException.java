package com.anderson.edescart.services.exceptions;

public class WebClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WebClientException(String msg) {
		super(msg);
	}
}
