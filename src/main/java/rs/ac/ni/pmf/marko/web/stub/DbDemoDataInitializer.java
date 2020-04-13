package rs.ac.ni.pmf.marko.web.stub;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.service.MessagesService;
import rs.ac.ni.pmf.marko.web.service.TicketsService;

@RequiredArgsConstructor
@Component
@Slf4j
public class DbDemoDataInitializer implements InitializingBean {

	private final TicketsService ticketsService;
	private final MessagesService messagesService;

	@Override
	public void afterPropertiesSet() throws Exception {
		DbDemoDataInitializer.log.info("Initializing DbDataProvider, after properties set");

		final TicketDTO ticketDto = TicketDTO.builder()
				.user("Marko")
				.title("First ticket")
				.description("No description")
				.build();

		final TicketDTO savedTicketDTO = ticketsService.saveTicket(ticketDto);

		final MessageDTO messageDto1 = MessageDTO.builder()
				.user("Pera")
				.title("Response to ticket")
				.content("Problem solved")
				.build();

		final MessageDTO messageDto2 = MessageDTO.builder()
				.user("Mika")
				.title("Another response to ticket")
				.content("Problem not solved")
				.build();

		final Integer savedTicketId = savedTicketDTO.getId();
		final MessageDTO savedMessage1 = messagesService.saveMessage(savedTicketId, messageDto1, null);
		messagesService.saveMessage(savedTicketId, messageDto2, savedMessage1.getId());
	}
}
