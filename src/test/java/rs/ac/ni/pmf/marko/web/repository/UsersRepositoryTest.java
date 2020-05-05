package rs.ac.ni.pmf.marko.web.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.ni.pmf.marko.web.config.PersistenceConfiguration;
import rs.ac.ni.pmf.marko.web.model.UsersSearchOptions;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.repository.specification.UsersSearchSpecification;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { TestDatabaseConfiguration.class })
@ContextConfiguration(classes = { PersistenceConfiguration.class, UsersRepository.class })
public class UsersRepositoryTest {

	@Autowired
	private UsersRepository usersRepository;
	
	@Test
	public void shouldAddUser() {
		usersRepository.deleteAll();
		
		assertThat(usersRepository.count()).isEqualTo(0);
		
		final UserEntity user = UserEntity.builder()
				.firstName("Marko")
				.lastName("Milosevic")
				.username("markom")
				.build();
		
		usersRepository.save(user);
		
		assertThat(usersRepository.count()).isEqualTo(1);
		
		final UserEntity updateUser = UserEntity.builder()
				.firstName("Mika")
				.lastName("Mikic")
				.username("markom")
				.build();
		
		usersRepository.save(updateUser);
		
		assertThat(usersRepository.count()).isEqualTo(1);
		
		final UsersSearchOptions options = UsersSearchOptions.builder()
				.firstNameFilter("mika")
				.build();
		
		final List<UserEntity> users = usersRepository.findAll(new UsersSearchSpecification(options));
		
		assertThat(users).isNotEmpty();
		assertThat(users.get(0).getFirstName()).isEqualTo("Mika");
	}
}
