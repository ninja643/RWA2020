package rs.ac.ni.pmf.marko.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 100)
	private String user;
	
	private String title;
	
	private String description;

//	@Default
//	private List<MessageEntity> messages = new ArrayList();
}
