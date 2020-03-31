package rs.ac.ni.pmf.marko.web.model.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

@Value
@Builder
public class TicketEntity {
	private Integer id;
	private String user;
	private String title;
	private String description;

	@Default
	private List<MessageEntity> messages = new ArrayList();
}
