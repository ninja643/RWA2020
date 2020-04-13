package rs.ac.ni.pmf.marko.web.exception;

import lombok.Getter;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;

@Getter
public class ResourceException extends Exception {
	private static final long serialVersionUID = 1L;

	private final ErrorInfo.ResourceType resourceType;

	public ResourceException(final ResourceType resourceType, final String message) {
		super(message);
		this.resourceType = resourceType;
	}
}
