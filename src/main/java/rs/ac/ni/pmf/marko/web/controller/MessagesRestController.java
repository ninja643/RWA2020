package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;

@RequestMapping(path = "/tickets/{ticketId}/messages")
public interface MessagesRestController {

	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<MessageDTO> getMessages(@PathVariable(name = "ticketId") int ticketId);

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<MessageDTO> getMessage(@PathVariable(name = "ticketId") int ticketId, @PathVariable(name = "id") int id);

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	MessageDTO saveMessage(@PathVariable(name = "ticketId") int ticketId, @RequestBody MessageDTO message);

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	void deleteMessage(@PathVariable(name = "ticketId") int ticketId, @PathVariable(name = "id") int id);
}
