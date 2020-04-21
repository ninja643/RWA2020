package rs.ac.ni.pmf.marko.web.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsersSearchOptions {
	private String usernameFilter;
	private String firstNameFilter;
	private String lastNameFilter;
	
	private Integer minTicketsCount;

	private Integer page;
	private Integer size;
}
