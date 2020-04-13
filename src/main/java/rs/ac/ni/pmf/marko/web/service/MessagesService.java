package rs.ac.ni.pmf.marko.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.model.mapper.MessagesMapper;
import rs.ac.ni.pmf.marko.web.repository.MessagesRepository;
import rs.ac.ni.pmf.marko.web.repository.TicketsRepository;
import rs.ac.ni.pmf.marko.web.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class MessagesService {

	private final TicketsRepository ticketsRepository;
	private final MessagesRepository messagesRepository;
	private final MessagesMapper messagesMapper;
	private final UsersRepository usersRepository;

	public List<MessageDTO> getMessages(final int ticketId) throws ResourceNotFoundException {

		if (!ticketsRepository.existsById(ticketId)) {
			throw new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + ticketId + "' not found");
		}

		return messagesRepository.findByTicketId(ticketId).stream().map(messagesMapper::toDto)
				.collect(Collectors.toList());
	}

//	@Transactional
	public MessageDTO saveMessage(final int ticketId, final MessageDTO message, final Integer replyToId)
			throws DuplicateResourceException, ResourceNotFoundException {

		final TicketEntity ticketEntity = ticketsRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.TICKET,
						"Ticket with id '" + ticketId + "' not found"));

		final UserEntity userEntity = usersRepository.findById(message.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, "User '" + message.getUsername() + "' not found"));

		final MessageEntity replyToEntity = replyToId != null ? messagesRepository.findById(replyToId).orElse(null) : null;
		final MessageEntity messageEntity = messagesMapper.toEntity(message, userEntity, ticketEntity, replyToEntity);
		final MessageEntity savedEntity = messagesRepository.save(messageEntity);

//		ticketEntity.getMessages().add(messageEntity);
//		ticketsRepository.save(ticketEntity);

		return messagesMapper.toDto(savedEntity);
	}

	public void deleteMessage(final int ticketId, final int messageId) throws ResourceNotFoundException {

	}
}
