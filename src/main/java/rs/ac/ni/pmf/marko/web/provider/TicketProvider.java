package rs.ac.ni.pmf.marko.web.provider;

import java.util.List;

import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;

public interface TicketProvider {

	List<TicketEntity> getAllTickets();
	
	TicketEntity getTicket(int id) throws ResourceNotFoundException;
		
	TicketEntity saveTicket(TicketEntity ticket) throws DuplicateResourceException;
	
	TicketEntity updateTicket(TicketEntity ticket);
	
	void deleteTicket(int id) throws ResourceNotFoundException;
}
