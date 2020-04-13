package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.ni.pmf.marko.web.controller.TicketsRestController;
import rs.ac.ni.pmf.marko.web.exception.BadRequestException;
import rs.ac.ni.pmf.marko.web.exception.ResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.service.TicketsService;

@RestController
public class TicketsRestControllerImpl implements TicketsRestController {

	@Autowired
	private TicketsService ticketsService;

	@Override
	public List<TicketDTO> getAllTickets() {
		return ticketsService.getAllTickets();
	}

	@Override
	public TicketDTO getTicket(final int id) throws ResourceNotFoundException {
		return ticketsService.getTicket(id);
	}

	@Override
	public TicketDTO saveTicket(final TicketDTO ticket) throws ResourceException, BadRequestException {
		return ticketsService.saveTicket(ticket);
	}

	@Override
	public void updateTicket(final int id, final TicketDTO ticket) throws ResourceException, BadRequestException {
		ticketsService.updateTicket(id, ticket);
	}

	@Override
	public void deleteTicket(final int id) throws ResourceNotFoundException {
		ticketsService.deleteTicket(id);
	}
}
