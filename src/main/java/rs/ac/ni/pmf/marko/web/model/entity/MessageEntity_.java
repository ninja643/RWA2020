package rs.ac.ni.pmf.marko.web.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MessageEntity.class)
public class MessageEntity_ {
	public static volatile SingularAttribute<MessageEntity, Integer> id;
	public static volatile SingularAttribute<MessageEntity, String> title;
	public static volatile SingularAttribute<MessageEntity, String> content;

	public static volatile SingularAttribute<MessageEntity, TicketEntity> ticket;
	public static volatile SingularAttribute<MessageEntity, MessageEntity> replyTo;
}
