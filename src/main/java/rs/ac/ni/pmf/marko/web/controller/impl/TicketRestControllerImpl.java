package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.ni.pmf.marko.web.controller.TicketRestController;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.provider.TicketProvider;

@RestController
@RequestMapping(path = "/tickets")
public class TicketRestControllerImpl implements TicketRestController{

	@Autowired
	private TicketProvider ticketProvider;

	@Override
	public List<TicketDTO> getAllTickets() {
		return ticketProvider.getAllTickets();
	}

	@Override
	public TicketDTO getTicket(@PathVariable(name = "id") int id) {
		return ticketProvider.getTicket(id);
	}

	@Override
	public TicketDTO saveTicket(@RequestBody TicketDTO ticket) {
		return ticketProvider.saveTicket(ticket);
	}

	@Override
	public TicketDTO updateTicket(int id, TicketDTO ticket) {
		// Not checking the id here, yet
		return ticketProvider.updateTicket(ticket);
	}

	@Override
	public void updateTicket(int id) {
		ticketProvider.deleteTicket(id);
	}
}
