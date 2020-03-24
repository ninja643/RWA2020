package rs.ac.ni.pmf.marko.web.provider;

import java.util.List;

import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;

public interface TicketProvider {

	List<TicketEntity> getAllTickets();
	
	TicketEntity getTicket(int id);
		
	TicketEntity saveTicket(TicketEntity ticket);
	
	TicketEntity updateTicket(TicketEntity ticket);
	
	void deleteTicket(int id);
}
