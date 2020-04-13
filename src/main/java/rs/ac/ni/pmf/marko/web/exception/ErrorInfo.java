package rs.ac.ni.pmf.marko.web.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {
	private ErrorCode errorCode;
	private ResourceType resourceType;

	private String message;

	public enum ErrorCode {
		NOT_FOUND,
		DUPLICATE
	}

	public enum ResourceType {
		TICKET,
		MESSAGE,
		USER
	}
}
