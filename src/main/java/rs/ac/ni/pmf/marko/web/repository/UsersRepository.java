package rs.ac.ni.pmf.marko.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

public interface UsersRepository extends PagingAndSortingRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {  
	//CrudRepository<UserEntity, String> {

	@Override
	List<UserEntity> findAll();
	
//	List<UserEntity> findByLastNameOrFirstNameOrderByLastNameDesc(String lastName, String firstName);
}
