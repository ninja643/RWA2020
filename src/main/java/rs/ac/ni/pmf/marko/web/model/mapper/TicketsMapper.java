package rs.ac.ni.pmf.marko.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

@Component
public class TicketsMapper {

	public TicketDTO toDto(final TicketEntity ticketEntity) {
		return TicketDTO.builder()
				.id(ticketEntity.getId())
				.title(ticketEntity.getTitle())
				.description(ticketEntity.getDescription())
				.username(ticketEntity.getUser().getUsername())
				.build();
	}

	public TicketEntity toEntity(final TicketDTO ticketDTO, final UserEntity userEntity) {
		return TicketEntity.builder()
				.id(ticketDTO.getId())
				.title(ticketDTO.getTitle())
				.description(ticketDTO.getDescription())
				.user(userEntity)
				.build();
	}
}
