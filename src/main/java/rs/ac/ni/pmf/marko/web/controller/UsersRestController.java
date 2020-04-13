package rs.ac.ni.pmf.marko.web.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.ac.ni.pmf.marko.web.model.api.UserDTO;

@RequestMapping(path = "/users")
public interface UsersRestController {

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	List<UserDTO> getUsers();

	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	UserDTO getUser(@PathVariable(name = "username", required = true) String username);
}
