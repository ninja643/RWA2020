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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;

@RequestMapping(path = "/tickets/{ticketId}/messages")
public interface MessagesRestController {

	@ApiOperation(value = "Get the list of messages for the given ticket")
	@ApiResponses(
		value = { @ApiResponse(code = 200, message = "The list of messages was successfuly retrieved"),
				@ApiResponse(code = 404, message = "Something went wrong. Probably the given id does not exist") })
	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<MessageDTO> getMessages(
			@ApiParam(value = "Id of a ticket that we're retrieving messages for", example = "1") @PathVariable(name = "ticketId") int ticketId)
			throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	MessageDTO saveMessage(
			@PathVariable(name = "ticketId") int ticketId,
			@RequestBody MessageDTO message,
			@RequestParam(name = "replyToId", required = false) Integer replyToId)
			throws DuplicateResourceException, ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	void deleteMessage(@PathVariable(name = "ticketId") int ticketId, @PathVariable(name = "id") int messageId)
			throws ResourceNotFoundException;
}
