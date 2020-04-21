package rs.ac.ni.pmf.marko.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {  

	@Override
	List<UserEntity> findAll();
	
//	List<UserEntity> findByLastNameOrFirstNameOrderByLastNameDesc(String lastName, String firstName);
}
