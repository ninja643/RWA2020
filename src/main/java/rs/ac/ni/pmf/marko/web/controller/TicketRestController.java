package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;

@RequestMapping(path = "/default")
public interface TicketRestController {

	/*
	 * GET /services/rest/tickets
	 */
	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<TicketDTO> getAllTickets();

	/*
	 * GET /services/rest/tickets/{id}
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	TicketDTO getTicket(@PathVariable(name = "id") int id);

	/*
	 * POST /services/rest/tickets
	 */
	@RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	TicketDTO saveTicket(@RequestBody TicketDTO ticket);
	
	/*
	 * PUT /services/rest/tickets
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	TicketDTO updateTicket(@PathVariable(name = "id") int id, @RequestBody TicketDTO ticket);
	
	/*
	 * DELETE /services/rest/tickets/{id}
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	void updateTicket(@PathVariable(name = "id") int id);
}
