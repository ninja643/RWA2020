package rs.ac.ni.pmf.marko.web.exception;

import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;

public class ResourceNotFoundException extends ResourceException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final ResourceType resourceType, final String message) {
		super(resourceType, message);
	}
}
