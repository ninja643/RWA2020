package rs.ac.ni.pmf.marko.web.model.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MessageEntity {
	
	int id;
	
	TicketEntity ticket;
	
	MessageEntity replyTo;
	
	String user;
	String title;
	String content;
}
