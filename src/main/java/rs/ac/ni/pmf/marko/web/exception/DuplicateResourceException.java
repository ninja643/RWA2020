package rs.ac.ni.pmf.marko.web.exception;

import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;

public class DuplicateResourceException extends ResourceException {
	private static final long serialVersionUID = 1L;

	public DuplicateResourceException(ResourceType resourceType, String message) {
		super(resourceType, message);
	}
}
