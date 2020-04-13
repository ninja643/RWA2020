package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;

@RequestMapping(path = "/tickets/{ticketId}/messages")
public interface MessagesRestController {

	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<MessageDTO> getMessages(@PathVariable(name = "ticketId") int ticketId) throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	MessageDTO saveMessage(@PathVariable(name = "ticketId") int ticketId,
			@RequestBody MessageDTO message,
			@RequestParam(name = "replyToId", required = false) Integer replyToId)
			throws DuplicateResourceException, ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	void deleteMessage(@PathVariable(name = "ticketId") int ticketId, @PathVariable(name = "id") int messageId)
			throws ResourceNotFoundException;
}
