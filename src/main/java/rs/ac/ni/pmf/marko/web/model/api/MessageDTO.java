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
	int id;
	int ticketId;
	Integer replyToId;
	String user;
	String title;
	String content;
}
