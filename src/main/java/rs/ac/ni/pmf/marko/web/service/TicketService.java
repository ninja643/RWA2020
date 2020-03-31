package rs.ac.ni.pmf.marko.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.mapper.TicketMapper;
import rs.ac.ni.pmf.marko.web.provider.DataProvider;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final DataProvider ticketProvider;
	private final TicketMapper ticketMapper;

	public List<TicketDTO> getAllTickets() {
		return ticketProvider.getAllTickets().stream().map(ticketMapper::toDto).collect(Collectors.toList());
	}

	public TicketDTO getTicket(int id) throws ResourceNotFoundException {
		return ticketMapper.toDto(ticketProvider.getTicket(id));
	}

	public TicketDTO saveTicket(TicketDTO ticket) throws DuplicateResourceException {
		final TicketEntity savedEntity = ticketProvider.saveTicket(ticketMapper.toEntity(ticket));
		
		return ticketMapper.toDto(savedEntity);
	}

	public TicketDTO updateTicket(int id, TicketDTO ticket) throws DuplicateResourceException {
		final TicketEntity ticketEntity = ticketMapper.toEntity(id, ticket);
		
		return ticketMapper.toDto(ticketProvider.updateTicket(id, ticketEntity));
	}

	public void deleteTicket(int id) throws ResourceNotFoundException {
		ticketProvider.deleteTicket(id);
	}

}
