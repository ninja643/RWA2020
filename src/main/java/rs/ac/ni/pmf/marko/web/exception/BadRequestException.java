package rs.ac.ni.pmf.marko.web.exception;

public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;

	public BadRequestException(final String message) {
		super(message);
	}
}
