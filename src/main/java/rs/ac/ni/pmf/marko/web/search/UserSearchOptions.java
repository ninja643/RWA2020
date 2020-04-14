package rs.ac.ni.pmf.marko.web.search;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserSearchOptions {
	String firstNameFilter;
	String lastNameFilter;
	
	int offset;
	int limit;
}
