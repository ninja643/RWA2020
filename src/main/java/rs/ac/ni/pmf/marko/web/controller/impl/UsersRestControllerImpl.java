package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.controller.UsersRestController;
import rs.ac.ni.pmf.marko.web.exception.BadRequestException;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.model.UsersSearchOptions;
import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.model.api.UserTicketLiteDTO;
import rs.ac.ni.pmf.marko.web.service.UsersService;

@RestController
@RequiredArgsConstructor
public class UsersRestControllerImpl implements UsersRestController {

	private final UsersService usersService;

	@Override
	public Page<UserDTO> getUsers(
			final String firstNameFilter,
			final String lastNameFilter,
			final Integer page,
			final Integer pageSize,
			final Integer minTicketsCount) {
		final UsersSearchOptions searchOptions = UsersSearchOptions.builder()
				.firstNameFilter(firstNameFilter)
				.lastNameFilter(lastNameFilter)
				.page(page)
				.size(pageSize)
				.minTicketsCount(minTicketsCount)
				.build();

		return usersService.getAllUsers(searchOptions);
	}

	@Override
	public UserDTO getUser(final String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTicketLiteDTO> getTicketTitles(String username) {
		return usersService.getTicketsLite(username);
	}

	@Override
	public long getMessagesCount(String username) {
		return usersService.countMessages(username);
	}

	@Override
	public UserDTO addUser(UserDTO user) throws DuplicateResourceException, BadRequestException {
		return usersService.addUser(user);
	}

}
