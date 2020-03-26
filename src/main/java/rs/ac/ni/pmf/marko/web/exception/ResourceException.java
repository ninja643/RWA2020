package rs.ac.ni.pmf.marko.web.exception;

import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;

public class ResourceException extends Exception {
	private static final long serialVersionUID = 1L;

	private final ErrorInfo.ResourceType resourceType;
	
	public ResourceException(ResourceType resourceType, String message) {
		super(message);
		this.resourceType = resourceType;
	}

	public ErrorInfo.ResourceType getResourceType() {
		return resourceType;
	}
}
