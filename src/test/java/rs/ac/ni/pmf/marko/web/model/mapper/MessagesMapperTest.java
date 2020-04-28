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
		
		final String messageTitle = "Message title";
		final String username = "markom";

		final UserEntity userEntity = UserEntity.builder()
				.username(username)
				.firstName("Marko")
				.lastName("Milosevic")
				.build();
		
		final TicketEntity ticketEntity = TicketEntity.builder()
				.id(10)
				.title("Ticket title")
				.description("Ticket description")
				.user(userEntity)
				.build();
		
		final MessageEntity messageEntity = MessageEntity.builder()
				.id(1)
				.user(userEntity)
				.ticket(ticketEntity)
				.title(messageTitle)
				.content("Message content")
				.build();
		
		final MessageDTO expectedDto = MessageDTO.builder()
				.id(1)
				.username(username)
				.ticketId(10)
				.title(messageTitle)
				.content("Message content")
				.build();
		
		final MessageDTO actualDto = mapper.toDto(messageEntity);
		
		assertThat(actualDto).isEqualTo(expectedDto);
	}
	
	@Test
	public void shouldConvertToDtoWithReplyTo() {
		final String username = "markom";
		final String messageTitle = "Message title";
		final String messagecContent = "Message content";
		final int messageId = 1;
		final int ticketId = 10;
		final int replyToId = 5;
		
		final UserEntity userEntity = mock(UserEntity.class);
		when(userEntity.getUsername()).thenReturn(username);
		
		final TicketEntity ticketEntity = mock(TicketEntity.class);
		when(ticketEntity.getId()).thenReturn(ticketId);
		
		final MessageEntity replyTo = mock(MessageEntity.class);
		when(replyTo.getId()).thenReturn(replyToId);
		
		final MessageEntity messageEntity = MessageEntity.builder()
				.id(messageId)
				.user(userEntity)
				.ticket(ticketEntity)
				.title(messageTitle)
				.replyTo(replyTo)
				.content(messagecContent)
				.build();
		
		final MessageDTO actualDto = mapper.toDto(messageEntity);
		
		assertThat(actualDto.getId()).isEqualTo(messageId);
		assertThat(actualDto.getReplyToId()).isEqualTo(replyToId);
	}
}
