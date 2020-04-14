package rs.ac.ni.pmf.marko.web.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "username")
	private UserEntity user;
	
	private String title;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id")
	private TicketEntity ticket;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reply_to_id")
	private MessageEntity replyTo;

	@OneToMany(mappedBy = "replyTo")
	private List<MessageEntity> repliedFrom;
}
