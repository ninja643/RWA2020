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
import rs.ac.ni.pmf.marko.web.provider.TicketProvider;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketProvider ticketProvider;
	private final TicketMapper ticketMapper;

	public List<TicketDTO> getAllTickets() {

		return ticketProvider.getAllTickets().stream().map(ticketMapper::toDto).collect(Collectors.toList());

//		return ticketProvider.getAllTickets().stream()
//				.map(ticketEntity -> ticketMapper.toDto(ticketEntity))
//				.collect(Collectors.toList());

		/*
		 * return ticketProvider.getAllTickets().stream() .map(ticketEntity -> { return
		 * ticketMapper.toDto(ticketEntity); }) .collect(Collectors.toList());
		 */

		/*
		 * final List<TicketEntity> ticketEntities = ticketProvider.getAllTickets();
		 * 
		 * final List<TicketDTO> ticketDtos = new ArrayList<>();
		 * 
		 * for (final TicketEntity ticketEntity : ticketEntities) {
		 * ticketDtos.add(ticketMapper.toDto(ticketEntity)); }
		 * 
		 * return ticketDtos;
		 */
	}

	public TicketDTO getTicket(int id) throws ResourceNotFoundException {
		return ticketMapper.toDto(ticketProvider.getTicket(id));
	}

	public TicketDTO saveTicket(TicketDTO ticket) throws DuplicateResourceException {
		TicketEntity savedEntity = ticketProvider.saveTicket(ticketMapper.toEntity(ticket));
		return ticketMapper.toDto(savedEntity);
	}

	public TicketDTO updateTicket(TicketDTO ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteTicket(int id) throws ResourceNotFoundException {
		ticketProvider.deleteTicket(id);
	}

}
