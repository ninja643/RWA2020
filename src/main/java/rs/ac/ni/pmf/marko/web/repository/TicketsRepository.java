package rs.ac.ni.pmf.marko.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;

@Repository
public interface TicketsRepository extends CrudRepository<TicketEntity, Integer>, JpaSpecificationExecutor<TicketEntity> {

	@Override
	List<TicketEntity> findAll();
}
