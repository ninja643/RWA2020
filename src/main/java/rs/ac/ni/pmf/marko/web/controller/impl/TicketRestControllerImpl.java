package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.ni.pmf.marko.web.controller.TicketRestController;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.service.TicketService;

@RestController
public class TicketRestControllerImpl implements TicketRestController{

	@Autowired
	private TicketService ticketService;
	
	@Override
	public List<TicketDTO> getAllTickets() {
		return ticketService.getAllTickets();
	}

	@Override
	public TicketDTO getTicket(int id) {
		return ticketService.getTicket(id);
	}

	@Override
	public TicketDTO saveTicket(TicketDTO ticket) {
		return ticketService.saveTicket(ticket);
	}

	@Override
	public TicketDTO updateTicket(int id, TicketDTO ticket) {
		// Not checking the id here, yet
		return ticketService.updateTicket(ticket);
	}

	@Override
	public void deleteTicket(int id) {
		ticketService.deleteTicket(id);
	}
}
