package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.provider.TicketProvider;

@RestController
@RequestMapping(path = "/tickets")
public class TicketRestController {

	@Autowired
	private TicketProvider ticketProvider;

	/*
	 * GET /services/rest/tickets
	 */
	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketDTO> getAllTickets() {
		return ticketProvider.getAllTickets();
	}

	/*
	 * GET /services/rest/tickets/{id}
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDTO getTicket(@PathVariable(name = "id") int id) {
		return ticketProvider.getTicket(id);
	}

	/*
	 * POST /services/rest/tickets
	 */
	@RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TicketDTO saveTicket(@RequestBody TicketDTO ticket) {
		return ticketProvider.saveTicket(ticket);
	}
}
