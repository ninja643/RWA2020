package rs.ac.ni.pmf.marko.web.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.provider.TicketProvider;

public class TicketMemoryProvider implements TicketProvider {

	private Map<Integer, TicketEntity> tickets = new HashMap<Integer, TicketEntity>();

	public TicketMemoryProvider() {
		tickets.put(1, TicketEntity.builder().id(1).user("Marko").title("Ticket 1")
				.description("This is the first ticket").build());
		tickets.put(2, TicketEntity.builder().id(2).user("Marko").title("Ticket 2")
				.description("This is the second ticket").build());
	}

	@Override
	public List<TicketEntity> getAllTickets() {
		return new ArrayList<TicketEntity>(tickets.values());
	}

	@Override
	public TicketEntity getTicket(int id) throws ResourceNotFoundException {

		final TicketEntity ticketEntity = tickets.get(id);

		if (ticketEntity == null) {
			throw new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found");
		}

		return ticketEntity;
	}

	@Override
	public TicketEntity saveTicket(TicketEntity ticket) throws DuplicateResourceException {
		int ticketId = ticket.getId();

		if (tickets.containsKey(ticketId)) {
			throw new DuplicateResourceException(ResourceType.TICKET, "Ticket with id '" + ticketId + "' already exists");
		}
		
		return tickets.put(ticketId, ticket);
	}

	@Override
	public TicketEntity updateTicket(TicketEntity ticket) {
		return tickets.put(ticket.getId(), ticket);
	}

	@Override
	public void deleteTicket(int id) throws ResourceNotFoundException {
		
		if (!tickets.containsKey(id)) {
			throw new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found");
		}

		tickets.remove(id);
	}

}
