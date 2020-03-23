package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;

@RequestMapping(path = "/tickets/{ticketId}/messages")
public interface MessagesRestController {
	
	@RequestMapping(method = RequestMethod.GET, path = "")
	List<MessageDTO> getMessages(@PathVariable(name = "ticketId") int ticketId);
}
