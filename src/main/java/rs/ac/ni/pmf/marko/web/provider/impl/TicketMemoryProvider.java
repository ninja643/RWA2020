package rs.ac.ni.pmf.marko.web.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.provider.TicketProvider;

public class TicketMemoryProvider implements TicketProvider {

	private Map<Integer, TicketDTO> tickets = new HashMap<Integer, TicketDTO>();

	public TicketMemoryProvider() {
		tickets.put(1, TicketDTO.builder().id(1).user("Marko").title("Ticket 1").description("This is the first ticket")
				.build());
		tickets.put(2, TicketDTO.builder().id(2).user("Marko").title("Ticket 2")
				.description("This is the second ticket").build());
	}

	@Override
	public List<TicketDTO> getAllTickets() {
		return new ArrayList<TicketDTO>(tickets.values());
	}

	@Override
	public TicketDTO getTicket(int id) {
		return tickets.get(id);
	}

	@Override
	public TicketDTO saveTicket(TicketDTO ticket) {
		return tickets.put(ticket.getId(), ticket);
	}

	@Override
	public TicketDTO updateTicket(TicketDTO ticket) {
		return tickets.put(ticket.getId(), ticket);
	}

	@Override
	public void deleteTicket(int id) {
		tickets.remove(id);
	}

}
