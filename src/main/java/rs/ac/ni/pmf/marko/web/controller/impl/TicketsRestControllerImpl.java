package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.controller.TicketsRestController;
import rs.ac.ni.pmf.marko.web.exception.BadRequestException;
import rs.ac.ni.pmf.marko.web.exception.ResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.service.TicketsService;

@RestController
@RequiredArgsConstructor
public class TicketsRestControllerImpl implements TicketsRestController {

	private final TicketsService ticketsService;

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
