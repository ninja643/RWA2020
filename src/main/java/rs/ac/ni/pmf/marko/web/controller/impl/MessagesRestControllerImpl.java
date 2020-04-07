package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.controller.MessagesRestController;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.service.MessagesService;

@RestController
@RequiredArgsConstructor
public class MessagesRestControllerImpl implements MessagesRestController {

	private final MessagesService messagesService;

	@Override
	public List<MessageDTO> getMessages(int ticketId) throws ResourceNotFoundException {
		return messagesService.getMessages(ticketId);
	}

	@Override
	public MessageDTO getMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		return messagesService.getMesssage(ticketId, messageId);
	}

	@Override
	public MessageDTO saveMessage(int ticketId, MessageDTO message)
			throws DuplicateResourceException, ResourceNotFoundException {
		return messagesService.saveMessage(ticketId, message);
	}

	@Override
	public void deleteMessage(int ticketId, int messageId) throws ResourceNotFoundException {
		messagesService.deleteMessage(ticketId, messageId);
	}

}
