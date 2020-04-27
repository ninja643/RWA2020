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
import rs.ac.ni.pmf.marko.web.service.MessagesService;

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
	private MessagesService messagesService;

	@Test
	public void shouldGetAllMessagesForTicket() throws ResourceNotFoundException {
		final int id = 1;

		final MessageEntity messageEntity1 = mock(MessageEntity.class);
		final MessageEntity messageEntity2 = mock(MessageEntity.class);
		final List<MessageEntity> entities = Arrays.asList(messageEntity1, messageEntity2);

		final MessageDTO messageDto1 = mock(MessageDTO.class);
		final MessageDTO messageDto2 = mock(MessageDTO.class);

		when(ticketsRepository.existsById(id)).thenReturn(true);
		when(messagesRepository.findByTicketId(id)).thenReturn(entities);
		when(messagesMapper.toDto(messageEntity1)).thenReturn(messageDto1);
		when(messagesMapper.toDto(messageEntity2)).thenReturn(messageDto2);

		assertThat(messagesService.getMessages(id)).containsExactly(messageDto1, messageDto2);
	}

	@Test /* (expected = ResourceNotFoundException.class) */
	public void shouldThrowNotFoundException() throws ResourceNotFoundException {
		final int id = 1;
		when(ticketsRepository.existsById(id)).thenReturn(false);

		assertThatThrownBy(() -> messagesService.getMessages(id))
				.isInstanceOf(ResourceNotFoundException.class)
				.hasMessage("Ticket with id '" + id + "' not found");
	}
}
