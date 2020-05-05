package rs.ac.ni.pmf.marko.web.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.marko.web.config.PersistenceConfiguration;
import rs.ac.ni.pmf.marko.web.model.UsersSearchOptions;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.repository.specification.UsersSearchSpecification;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { TestDatabaseConfiguration.class })
@ContextConfiguration(classes = { PersistenceConfiguration.class, UsersRepository.class, TicketsRepository.class })
@Slf4j
public class UsersRepositoryTest {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	@Before
	public void initializeData() {
		log.info("Initializing data");
		ticketsRepository.deleteAll();
		usersRepository.deleteAll();
		
		final UserEntity user1 = UserEntity.builder()
				.firstName("Marko")
				.lastName("Milosevic")
				.username("markom")
				.build();
		
		usersRepository.save(user1);
		
		final UserEntity user2 = UserEntity.builder()
				.firstName("Mika")
				.lastName("Mikic")
				.username("mika")
				.build();
		
		final UserEntity savedUser2 = usersRepository.save(user2);
		
		final TicketEntity ticketEntity = TicketEntity.builder()
				.title("Ticket title")
				.description("Ticket description")
				.user(savedUser2)
				.build();
		
		final TicketEntity savedTicket = ticketsRepository.save(ticketEntity);
		
		log.info(savedTicket.getTitle());
	}
	
	@Test
	public void shouldCountUsers() {
		assertThat(usersRepository.count()).isEqualTo(2);
	}
	
	@Test
	public void shouldUseUserSearchOptions()
	{
		final UsersSearchOptions options1 = UsersSearchOptions.builder()
				.firstNameFilter("mika")
				.build();
		
		final List<UserEntity> users = usersRepository.findAll(new UsersSearchSpecification(options1));
		
		assertThat(users).isNotEmpty();
		assertThat(users.get(0).getFirstName()).isEqualTo("Mika");
		
		final UsersSearchOptions options2 = UsersSearchOptions.builder()
				.firstNameFilter("   ")
				.lastNameFilter("")
				.build();
		
		assertThat(usersRepository.findAll(new UsersSearchSpecification(options2))).hasSize(2);
		
		final UsersSearchOptions options3 = UsersSearchOptions.builder()
				.firstNameFilter(null)
				.lastNameFilter("milosevic")
				.build();
		
		assertThat(usersRepository.findAll(new UsersSearchSpecification(options3))).hasSize(1);
		
		final UsersSearchOptions options4 = UsersSearchOptions.builder()
				.minTicketsCount(1)
				.build();
		
		final List<UserEntity> matchedUsers = usersRepository.findAll(new UsersSearchSpecification(options4));
		assertThat(matchedUsers.size()).isEqualTo(1);
	}

	@Test
	public void shouldSelectUsersWhenGivenMitTicketCountTwo() {
		final UsersSearchOptions options5 = UsersSearchOptions.builder()
				.minTicketsCount(2)
				.build();
		
		final List<UserEntity> matchedUsers2 = usersRepository.findAll(new UsersSearchSpecification(options5));
		assertThat(matchedUsers2.size()).isEqualTo(0);
	}
	
	@Test
	public void shouldSelectAllUsersWhenGivenMinTicketCountZero() {
		final UsersSearchOptions options = UsersSearchOptions.builder()
				.minTicketsCount(0)
				.build();
		
		final List<UserEntity> matchedUsers = usersRepository.findAll(new UsersSearchSpecification(options));
		assertThat(matchedUsers.size()).isEqualTo(2);		
	}
}
