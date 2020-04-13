package rs.ac.ni.pmf.marko.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, String> {

	@Override
	List<UserEntity> findAll();
}
