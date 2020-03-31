package rs.ac.ni.pmf.marko.web.model.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TicketDTO {
	private Integer id;
	private String user;
	private String title;
	private String description;
}
