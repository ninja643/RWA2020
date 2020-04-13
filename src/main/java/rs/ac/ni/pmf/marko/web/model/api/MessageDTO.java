package rs.ac.ni.pmf.marko.web.model.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class MessageDTO {
	Integer id;
	String username;
	int ticketId;
	Integer replyToId;
	String title;
	String content;
}
