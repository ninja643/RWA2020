package rs.ac.ni.pmf.marko.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.exception.BadRequestException;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.ResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.model.mapper.TicketsMapper;
import rs.ac.ni.pmf.marko.web.repository.TicketsRepository;
import rs.ac.ni.pmf.marko.web.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class TicketsService {

	private final TicketsRepository ticketsRepository;
	private final UsersRepository usersRepository;
	private final TicketsMapper ticketsMapper;

	public List<TicketDTO> getAllTickets() {
		return ticketsRepository.findAll().stream().map(ticketsMapper::toDto).collect(Collectors.toList());
	}

	public TicketDTO getTicket(final int id) throws ResourceNotFoundException {
		final TicketEntity ticketEntity = ticketsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found"));
		return ticketsMapper.toDto(ticketEntity);
	}

	public TicketDTO saveTicket(final TicketDTO ticketDto) throws ResourceException, BadRequestException {
		final String username = ticketDto.getUsername();
		
		if (username == null || username.trim().isEmpty()) {
			throw new BadRequestException("Username cannot be null or empty!");
		}

		final UserEntity userEntity = usersRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, "User '" + username + "' does not exist"));

		final Integer ticketId = ticketDto.getId();

		if (ticketId != null && ticketsRepository.existsById(ticketId)) {
			throw new DuplicateResourceException(ResourceType.TICKET,
					"Ticket with id '" + ticketId + "' already exists");
		}

		final TicketEntity ticketEntity = ticketsMapper.toEntity(ticketDto, userEntity);
		final TicketEntity savedEntity = ticketsRepository.save(ticketEntity);

		return ticketsMapper.toDto(savedEntity);
	}

	public TicketDTO updateTicket(final int id, final TicketDTO ticketDto) throws ResourceException, BadRequestException {

		if (ticketDto.getId() != null && id != ticketDto.getId()) {
			throw new BadRequestException("Cannot change the id of a ticket!");
		}

		final TicketEntity ticketEntity = ticketsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found"));

		ticketEntity.setTitle(ticketDto.getTitle());
		ticketEntity.setDescription(ticketDto.getDescription());

		final TicketEntity savedEntity = ticketsRepository.save(ticketEntity);

		return ticketsMapper.toDto(savedEntity);
	}

	public void deleteTicket(final int id) throws ResourceNotFoundException {
		if (!ticketsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.TICKET, "Ticket with id '" + id + "' not found");
		}

		ticketsRepository.deleteById(id);
	}

}
