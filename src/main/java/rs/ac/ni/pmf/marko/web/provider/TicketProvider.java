package rs.ac.ni.pmf.marko.web.provider;

import java.util.List;

import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;

public interface TicketProvider {

	List<TicketDTO> getAllTickets();
	
	TicketDTO getTicket(int id);
		
	TicketDTO saveTicket(TicketDTO ticket);
	
	TicketDTO updateTicket(TicketDTO ticket);
	
	void deleteTicket(int id);
}
