package rs.ac.ni.pmf.marko.web.model.entity;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TicketEntity {
	private int id;
	private String user;
	private String title;
	private String description;
	
	private List<MessageEntity> messages;
}
