package rs.ac.ni.pmf.marko.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.mapper.MessageMapper;
import rs.ac.ni.pmf.marko.web.provider.DataProvider;

@Service
@RequiredArgsConstructor
public class MessagesService {

	private final DataProvider dataProvider;
	private final MessageMapper messageMapper;
	
	public List<MessageDTO> getMessages(int ticketId) throws ResourceNotFoundException {
		
		return dataProvider.getMessages(ticketId)
				.stream()
				.map(messageMapper::toDto)
				.collect(Collectors.toList());
	}

	public MessageDTO getMesssage(int ticketId, int messageId) throws ResourceNotFoundException {
		return messageMapper.toDto(dataProvider.getMessage(ticketId, messageId));
	}

	public MessageDTO saveMessage(int ticketId, MessageDTO message) throws DuplicateResourceException, ResourceNotFoundException {
		final TicketEntity ticketEntity = dataProvider.getTicket(ticketId);
		final MessageEntity savedEntity = dataProvider.addMessage(ticketId, messageMapper.toEntity(message, ticketEntity, null));
		return messageMapper.toDto(savedEntity);
	}

	public void deleteMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		dataProvider.deleteMessage(ticketId, messageId);
	}
}
