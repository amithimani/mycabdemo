package com.newcab.backend_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not allocate the requested car.")
public class CarAlreadyInUseException extends RuntimeException {
}
