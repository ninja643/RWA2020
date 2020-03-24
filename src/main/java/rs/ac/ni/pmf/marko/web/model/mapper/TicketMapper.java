package rs.ac.ni.pmf.marko.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;

@Component
public class TicketMapper {
	
	public TicketDTO toDto(final TicketEntity ticketEntity) {
		return TicketDTO.builder()
				.id(ticketEntity.getId())
				.title(ticketEntity.getTitle())
				.description(ticketEntity.getDescription())
				.user(ticketEntity.getUser())
				.build();
	}

	public TicketEntity toEntity(final TicketDTO ticketDTO) {
		return TicketEntity.builder()
				.id(ticketDTO.getId())
				.title(ticketDTO.getTitle())
				.description(ticketDTO.getDescription())
				.user(ticketDTO.getUser())
				.build();
	}
}
