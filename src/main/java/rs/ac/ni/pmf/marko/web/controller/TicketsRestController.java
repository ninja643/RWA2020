package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.marko.web.exception.BadRequestException;
import rs.ac.ni.pmf.marko.web.exception.ResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;

@RequestMapping(path = "/tickets")
public interface TicketsRestController {

	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<TicketDTO> getAllTickets();

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	TicketDTO getTicket(@PathVariable(name = "id") int id) throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	TicketDTO saveTicket(@RequestBody TicketDTO ticket) throws ResourceException, BadRequestException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void updateTicket(@PathVariable(name = "id") int id, @RequestBody TicketDTO ticket) throws ResourceException, BadRequestException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	void deleteTicket(@PathVariable(name = "id") int id) throws ResourceNotFoundException;
}
