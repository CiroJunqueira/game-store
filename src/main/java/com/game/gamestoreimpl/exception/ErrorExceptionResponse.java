package com.game.gamestoreimpl.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp;

	private String message;

	private String details;

}
