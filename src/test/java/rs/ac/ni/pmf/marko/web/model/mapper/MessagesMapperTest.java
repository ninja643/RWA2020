package rs.ac.ni.pmf.marko.web.model.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

public class MessagesMapperTest {

	private MessagesMapper mapper = new MessagesMapper();

	@Test
	public void shouldConvertToDto() {
		final String title = "Title";
		final String firstName = "Marko";
		final String username = "markom";
		final String ticketDescription = "Ticket description";
		final String ticketTitle = "Ticket title";
		final String content = "Message content";
		final int ticketId = 10;

		final UserEntity userEntity = UserEntity.builder()
				.firstName(firstName)
				.username(username)
				.build();

		final TicketEntity ticketEntity = TicketEntity.builder()
				.id(ticketId)
				.description(ticketDescription)
				.title(ticketTitle)
				.build();

		final MessageEntity messageEntity = MessageEntity.builder()
				.id(1)
				.user(userEntity)
				.ticket(ticketEntity)
				.title(title)
				.content(content)
				.build();

		final MessageDTO expected = MessageDTO.builder()
				.id(1)
				.username(username)
				.ticketId(ticketId)
				.title(title)
				.content(content)
				.build();

		final MessageDTO actual = mapper.toDto(messageEntity);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void shouldConvertToEntity() {
		final UserEntity userEntity = mock(UserEntity.class);
		final TicketEntity ticketEntity = mock(TicketEntity.class);
		final MessageDTO messageDto = mock(MessageDTO.class);

		when(messageDto.getId()).thenReturn(1);

		final MessageEntity actual = mapper.toEntity(messageDto, userEntity, ticketEntity, null);

		assertThat(actual.getTicket()).isEqualTo(ticketEntity);
		assertThat(actual.getUser()).isEqualTo(userEntity);
		assertThat(actual.getId()).isEqualTo(1);
	}
}
