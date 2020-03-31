package rs.ac.ni.pmf.marko.web.provider;

import java.util.List;

import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;

public interface DataProvider {

	List<TicketEntity> getAllTickets();
	
	TicketEntity getTicket(int id) throws ResourceNotFoundException;
		
	TicketEntity saveTicket(TicketEntity ticket) throws DuplicateResourceException;
	
	TicketEntity updateTicket(int id, TicketEntity ticket) throws DuplicateResourceException;
	
	void deleteTicket(int id) throws ResourceNotFoundException;
	
	List<MessageEntity> getMessages(int ticketId) throws ResourceNotFoundException;
	
	MessageEntity getMessage(int ticketId, int messageId) throws ResourceNotFoundException;
	
	MessageEntity addMessage(int ticketId, MessageEntity messageEntity) throws DuplicateResourceException, ResourceNotFoundException;
	
	void deleteMessage(int ticketId, int messageId) throws ResourceNotFoundException;
}
