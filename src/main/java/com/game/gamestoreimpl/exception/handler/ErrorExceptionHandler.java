package com.game.gamestoreimpl.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.game.gamestoreimpl.exception.ErrorException;
import com.game.gamestoreimpl.exception.ErrorExceptionResponse;

@ControllerAdvice
@RestController
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ErrorException.class)
	public final ResponseEntity<ErrorExceptionResponse> errorException(Exception ex, WebRequest request) {
		ErrorExceptionResponse exceptionResponse = new ErrorExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
