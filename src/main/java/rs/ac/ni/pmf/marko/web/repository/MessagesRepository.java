package rs.ac.ni.pmf.marko.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;

@Repository
public interface MessagesRepository extends CrudRepository<MessageEntity, Integer> {

	List<MessageEntity> findByTicketId(int ticketId);
}
