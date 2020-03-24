package rs.ac.ni.pmf.marko.web.model.entity;

public class MessageEntity {
	
	int id;
	
	TicketEntity ticket;
	
	MessageEntity replyTo;
	
	String user;
	String title;
	String content;
}
