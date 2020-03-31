package rs.ac.ni.pmf.marko.web.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.provider.DataProvider;

public class DataMemoryProvider implements DataProvider {

	private Map<Integer, TicketEntity> tickets = new HashMap<Integer, TicketEntity>();

	public DataMemoryProvider() {
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
		return resolveTicket(id);
	}

	@Override
	public TicketEntity saveTicket(TicketEntity ticket) throws DuplicateResourceException {
		int ticketId = ticket.getId();

		if (tickets.containsKey(ticketId)) {
			throw new DuplicateResourceException(ResourceType.TICKET,
					"Ticket with id '" + ticketId + "' already exists");
		}

		return tickets.put(ticketId, ticket);
	}

	@Override
	public TicketEntity updateTicket(int id, TicketEntity ticket) throws DuplicateResourceException {
		if (ticket.getId() != id && tickets.containsKey(ticket.getId())) {
			throw new DuplicateResourceException(ResourceType.TICKET,
					"Cannot update ticket id to a different existing id");
		}

		if (ticket.getId() != id) {
			// Remove the old ticket
			tickets.remove(id);
		}
		
		return tickets.put(ticket.getId(), ticket);
	}

	@Override
	public void deleteTicket(int id) throws ResourceNotFoundException {

		if (!tickets.containsKey(id)) {
			throw new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found");
		}

		tickets.remove(id);
	}

	@Override
	public List<MessageEntity> getMessages(int ticketId) throws ResourceNotFoundException {
		
		final TicketEntity ticketEntity = resolveTicket(ticketId);

		return ticketEntity.getMessages();
	}

	@Override
	public MessageEntity getMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		final TicketEntity ticketEntity = resolveTicket(ticketId);
		
		return findMessage(messageId, ticketEntity.getMessages());
	}

	@Override
	public MessageEntity addMessage(int ticketId, MessageEntity messageEntity) throws DuplicateResourceException, ResourceNotFoundException {
		final TicketEntity ticketEntity = resolveTicket(ticketId);
		
		final List<MessageEntity> messages = ticketEntity.getMessages();
		
		if (messages.contains(messageEntity))
		{
			throw new DuplicateResourceException(ResourceType.MESSAGE, "Message with id '" + messageEntity.getId() + "' already exists in ticket '" + ticketId + "'");
		}
		
		messages.add(messageEntity);
		
		return messageEntity;
	}

	@Override
	public void deleteMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		final TicketEntity ticketEntity = resolveTicket(ticketId);

		final List<MessageEntity> messages = ticketEntity.getMessages();
		
		final MessageEntity messageToDelete = findMessage(messageId, messages);
		
		messages.remove(messageToDelete);
	}

	private MessageEntity findMessage(int messageId, final List<MessageEntity> messages) throws ResourceNotFoundException {
		return messages.stream()
				.filter(me -> me.getId() == messageId)
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.MESSAGE, "Message with id '" + messageId + "' not found"));
	}

	private TicketEntity resolveTicket(int ticketId) throws ResourceNotFoundException {
		return Optional.ofNullable(tickets.get(ticketId))
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + ticketId + "' not found"));
	}

}
