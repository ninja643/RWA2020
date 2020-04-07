package rs.ac.ni.pmf.marko.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;

@Component
public class MessageMapper {

	public MessageDTO toDto(final MessageEntity messageEntity) {
		return MessageDTO.builder()
				.id(messageEntity.getId())
//				.ticketId(messageEntity.getTicket().getId())
//				.replyToId(messageEntity.getReplyTo() != null ? messageEntity.getReplyTo().getId() : null)
				.user(messageEntity.getUser())
				.title(messageEntity.getTitle())
				.content(messageEntity.getContent())
				.build();
	}
	
	public MessageEntity toEntity(final MessageDTO messageDto, TicketEntity ticketEntity, MessageEntity replyTo) {
		return MessageEntity.builder()
				.id(messageDto.getId())
//				.ticket(ticketEntity)
//				.replyTo(replyTo)
				.user(messageDto.getUser())
				.title(messageDto.getTitle())
				.content(messageDto.getContent())
				.build();
	}
}
