package rs.ac.ni.pmf.marko.web.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.ni.pmf.marko.web.TestDatabaseConfiguration;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfiguration.class)
public class UsersRepositoryTest {

	@Autowired
	private UsersRepository usersRepository;

	@Before
	public void init() {
		usersRepository.deleteAll();

		final UserEntity user = UserEntity.builder()
				.firstName("Marko")
				.lastName("Milosevic")
				.username("markom")
				.build();

		usersRepository.save(user);
	}

	@Test
	public void shouldCountUsers() {
		assertThat(usersRepository.count()).isEqualTo(1);
	}

	@Test
	public void shouldUpdateUser() {
		final UserEntity originalUser = usersRepository.findById("markom").get();

		final UserEntity user = UserEntity.builder()
				.firstName("Marko2")
				.lastName("Milosevic2")
				.username("markom")
				.build();

		final UserEntity updatedUser = usersRepository.save(user);

		assertThat(originalUser.getUsername()).isEqualTo(updatedUser.getUsername());
		assertThat(originalUser.getFirstName()).isEqualTo("Marko");
		assertThat(updatedUser.getFirstName()).isEqualTo("Marko2");
	}
}
