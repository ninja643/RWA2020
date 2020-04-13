package rs.ac.ni.pmf.marko.web.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ErrorCode;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;

@ControllerAdvice
@ResponseBody
public class ErrorController {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorInfo handleNotFoundException(final ResourceNotFoundException e) {
		return ErrorInfo.builder()
				.errorCode(ErrorCode.NOT_FOUND)
				.resourceType(e.getResourceType())
				.message(e.getMessage()).build();
	}

	@ExceptionHandler(DuplicateResourceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorInfo handleDuplicateResourceException(final DuplicateResourceException e) {
		return ErrorInfo.builder()
				.errorCode(ErrorCode.DUPLICATE)
				.resourceType(e.getResourceType())
				.message(e.getMessage()).build();
	}
}
