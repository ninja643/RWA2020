package rs.ac.ni.pmf.marko.web.model.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class UsersMapper {

	private final PasswordEncoder passwordEncoder;

	public UserDTO toDto(final UserEntity userEntity) {
		return UserDTO.builder()
				.username(userEntity.getUsername())
				.firstName(userEntity.getFirstName())
				.lastName(userEntity.getLastName())
				.build();
	}

	public UserEntity toEntity(final UserDTO userDto) {
		return UserEntity.builder()
				.username(userDto.getUsername())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.build();
	}
}
