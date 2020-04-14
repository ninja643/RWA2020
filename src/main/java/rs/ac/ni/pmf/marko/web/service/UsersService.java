package rs.ac.ni.pmf.marko.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.model.mapper.UsersMapper;
import rs.ac.ni.pmf.marko.web.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;
	private final UsersMapper usersMapper;

	public List<UserDTO> getAllUsers() {
		return usersRepository.findAll().stream()
				.map(usersMapper::toDto)
				.collect(Collectors.toList());
	}

	public UserDTO getUser(final String username) throws ResourceNotFoundException {
		final UserEntity userEntity = usersRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, "User with username '" + username + "' does not exist"));

		return usersMapper.toDto(userEntity);
	}

	public UserDTO addUser(final UserDTO userDto) throws DuplicateResourceException {
		final String username = userDto.getUsername();

		if (usersRepository.existsById(username)) {
			throw new DuplicateResourceException(ResourceType.USER, "User '" + username + "' already exists!");
		}

		final UserEntity userEntity = usersMapper.toEntity(userDto);
		UserEntity savedEntity = usersRepository.save(userEntity);
		return usersMapper.toDto(savedEntity);
	}
	
	public void deleteUser(final String username) throws ResourceNotFoundException {
		if (!usersRepository.existsById(username)) {
			throw new ResourceNotFoundException(ResourceType.USER, "User '" + username + "' not found");
		}
		
		usersRepository.deleteById(username);
	}
}
