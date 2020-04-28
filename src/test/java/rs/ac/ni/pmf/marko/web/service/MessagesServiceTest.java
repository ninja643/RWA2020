package rs.ac.ni.pmf.marko.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.mapper.MessagesMapper;
import rs.ac.ni.pmf.marko.web.repository.MessagesRepository;
import rs.ac.ni.pmf.marko.web.repository.TicketsRepository;
import rs.ac.ni.pmf.marko.web.repository.UsersRepository;

@RunWith(MockitoJUnitRunner.class)
public class MessagesServiceTest {

	@Mock
	private MessagesRepository messagesRepository;

	@Mock
	private TicketsRepository ticketsRepository;

	@Mock
	private UsersRepository usersRepository;

	@Mock
	private MessagesMapper messagesMapper;

	@InjectMocks
	private MessagesService service;

	@Test
	public void shouldGetAllMessagesForTicket() throws ResourceNotFoundException {
		final int ticketId = 1;

		when(ticketsRepository.existsById(ticketId)).thenReturn(true);

		final MessageEntity messageEntity1 = mock(MessageEntity.class);
		final MessageEntity messageEntity2 = mock(MessageEntity.class);

		when(messagesRepository.findByTicketId(ticketId))
				.thenReturn(Arrays.asList(messageEntity1, messageEntity2));

		final MessageDTO messageDto1 = mock(MessageDTO.class);
		final MessageDTO messageDto2 = mock(MessageDTO.class);

		when(messagesMapper.toDto(messageEntity1)).thenReturn(messageDto1);
		when(messagesMapper.toDto(messageEntity2)).thenReturn(messageDto2);

		final List<MessageDTO> actual = service.getMessages(ticketId);
		assertThat(actual).containsExactly(messageDto1, messageDto2);
	}

	@Test /* (expected = ResourceNotFoundException.class) */
	public void shouldThrowResourceNotFoundExceptionWhenTicketIdDoesNotExist() throws ResourceNotFoundException {
		final int ticketId = 1;

		when(ticketsRepository.existsById(ticketId)).thenReturn(false);

		assertThatThrownBy(() -> service.getMessages(ticketId))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Ticket with id '" + ticketId + "' not found");
		
	}
}
