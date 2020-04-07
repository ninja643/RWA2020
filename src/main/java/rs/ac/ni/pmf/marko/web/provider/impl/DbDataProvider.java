package rs.ac.ni.pmf.marko.web.provider.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.provider.DataProvider;
import rs.ac.ni.pmf.marko.web.repository.MessagesRepository;
import rs.ac.ni.pmf.marko.web.repository.TicketsRepository;

@RequiredArgsConstructor
@Component
@Slf4j
public class DbDataProvider implements DataProvider {

	private final TicketsRepository ticketsRepository;
	private final MessagesRepository messagesRepository;

	@Override
	public List<TicketEntity> getAllTickets() {
		return ticketsRepository.findAll();
	}

	@Override
	public TicketEntity getTicket(int id) throws ResourceNotFoundException {
		return ticketsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found"));
	}

	@Override
	public TicketEntity saveTicket(TicketEntity ticket) throws DuplicateResourceException {
		final Integer ticketId = ticket.getId();

		if (ticketId != null && ticketsRepository.existsById(ticketId)) {
			throw new DuplicateResourceException(ResourceType.TICKET,
					"Ticket with id '" + ticketId + "' already exists");
		}

		final TicketEntity savedEntity = ticketsRepository.save(ticket);
		
		log.info("Saved ticket: {}", savedEntity.toString());
		
		return savedEntity;
	}

	@Override
	public TicketEntity updateTicket(int id, TicketEntity ticket) throws DuplicateResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTicket(int id) throws ResourceNotFoundException {
		ticketsRepository.deleteById(id);
	}

	@Override
	public List<MessageEntity> getMessages(int ticketId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageEntity getMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageEntity addMessage(int ticketId, MessageEntity messageEntity)
			throws DuplicateResourceException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

	}

}
