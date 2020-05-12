package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rs.ac.ni.pmf.marko.web.exception.BadRequestException;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.model.api.UserTicketLiteDTO;

@RequestMapping(path = "/users")
public interface UsersRestController {

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<UserDTO> getUsers(
			@RequestParam(name = "firstName", required = false) final String firstNameFilter,
			@RequestParam(name = "lastName", required = false) final String lastNameFilter,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize,
			@RequestParam(name = "minTicketsCount", required = false) final Integer minTicketsCount);

	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	UserDTO getUser(@PathVariable(name = "username", required = true) String username);

	@GetMapping(path = "/{username}/titles", produces = MediaType.APPLICATION_JSON_VALUE)
	List<UserTicketLiteDTO> getTicketTitles(@PathVariable(name = "username") final String username);

	@GetMapping(path = "/{username}/messages_cnt", produces = MediaType.APPLICATION_JSON_VALUE)
	long getMessagesCount(@PathVariable(name = "username") final String username);

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	UserDTO addUser(@RequestBody final UserDTO user) throws DuplicateResourceException, BadRequestException;
}
