package rs.ac.ni.pmf.marko.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

@Component
public class UsersMapper {

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
				.build();
	}
}
