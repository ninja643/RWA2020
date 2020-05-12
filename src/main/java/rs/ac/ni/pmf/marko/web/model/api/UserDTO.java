package rs.ac.ni.pmf.marko.web.model.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserDTO {
	private String username;
	private String firstName;
	private String lastName;
	private String password;
}
