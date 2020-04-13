package rs.ac.ni.pmf.marko.web.model.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class MessagesMapper {

	public MessageDTO toDto(final MessageEntity messageEntity) {
		return MessageDTO.builder()
				.id(messageEntity.getId())
				.username(messageEntity.getUser().getUsername())
				.ticketId(messageEntity.getTicket().getId())
				.replyToId(messageEntity.getReplyTo() != null ? messageEntity.getReplyTo().getId() : null)
				.title(messageEntity.getTitle())
				.content(messageEntity.getContent())
				.build();
	}

	public MessageEntity toEntity(
			final MessageDTO messageDto,
			final UserEntity userEntity,
			final TicketEntity ticketEntity,
			final MessageEntity replyTo) {

		return MessageEntity.builder()
				.id(messageDto.getId())
				.user(userEntity)
				.ticket(ticketEntity)
				.replyTo(replyTo)
				.title(messageDto.getTitle())
				.content(messageDto.getContent())
				.build();
	}
}
