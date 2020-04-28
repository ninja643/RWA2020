//package rs.ac.ni.pmf.marko.web.stub;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import rs.ac.ni.pmf.marko.web.model.mapper.UsersMapper;
//import rs.ac.ni.pmf.marko.web.repository.UsersRepository;
//import rs.ac.ni.pmf.marko.web.service.MessagesService;
//import rs.ac.ni.pmf.marko.web.service.TicketsService;
//import rs.ac.ni.pmf.marko.web.service.UsersService;
//
//@RequiredArgsConstructor
//@Component
//@Slf4j
//public class DbDemoDataInitializer implements InitializingBean {
//
//	private final TicketsService ticketsService;
//	private final MessagesService messagesService;
//	private final UsersService usersService;
//
//	private final UsersRepository usersRepository;
//	private final UsersMapper usersMapper;
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		DbDemoDataInitializer.log.info("Initializing DbDataProvider, after properties set");
//
////		usersRepository.find
//		
////		Page<UserEntity> page = usersRepository.findAll(PageRequest.of(0, 2, Direction.ASC, "firstName"));
////
////		System.out.println("Total users: " + page.getTotalElements());
////		page.map(usersMapper::toDto)
////				.forEach(dto -> System.out.println(dto.toString()));
//
////		Iterator<UserEntity> users = usersRepository.findAll(Sort.by(Order.asc("firstName"))).iterator();
////
////		while (users.hasNext()) {
////			System.out.println(usersMapper.toDto(users.next()).toString());
////		}
//
////		usersRepository.findByLastNameOrFirstNameOrderByLastNameDesc("Peric", "Marko")
////			.stream()
////			.map(usersMapper::toDto)
////			.forEach(dto -> System.out.println(dto));
//
////		final UserDTO userDto1 = UserDTO.builder()
////				.username("markom")
////				.firstName("Marko")
////				.lastName("Milosevic")
////				.build();
////		
////		usersService.addUser(userDto1);
////		
////		final UserDTO userDto2 = UserDTO.builder()
////				.username("pera")
////				.firstName("Pera")
////				.lastName("Peric")
////				.build();
////		
////		usersService.addUser(userDto2);
////		
////		final UserDTO userDto3 = UserDTO.builder()
////				.username("mika")
////				.firstName("Mika")
////				.lastName("Mikic")
////				.build();
////		
////		usersService.addUser(userDto3);
////		
////		final TicketDTO ticketDto = TicketDTO.builder()
////				.username("markom")
////				.title("First ticket")
////				.description("No description")
////				.build();
////
////		final TicketDTO savedTicketDTO = ticketsService.saveTicket(ticketDto);
////
////		final MessageDTO messageDto1 = MessageDTO.builder()
////				.username("pera")
////				.title("Response to ticket")
////				.content("Problem solved")
////				.build();
////
////		final MessageDTO messageDto2 = MessageDTO.builder()
////				.username("mika")
////				.title("Another response to ticket")
////				.content("Problem not solved")
////				.build();
////
////		final Integer savedTicketId = savedTicketDTO.getId();
////		final MessageDTO savedMessage1 = messagesService.saveMessage(savedTicketId, messageDto1, null);
////		messagesService.saveMessage(savedTicketId, messageDto2, savedMessage1.getId());
//
//	}
//
//}
