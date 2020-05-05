package rs.ac.ni.pmf.marko.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import rs.ac.ni.pmf.marko.web.TestWebConfiguration;
import rs.ac.ni.pmf.marko.web.controller.impl.TicketsRestControllerImpl;
import rs.ac.ni.pmf.marko.web.model.api.TicketDTO;
import rs.ac.ni.pmf.marko.web.service.TicketsService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestWebConfiguration.class })
public class TicketsRestControllerTest {

	@Mock
	private TicketsService ticketsService;

	@InjectMocks
	private TicketsRestControllerImpl ticketsController;

	private MockMvc mockMvc;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ticketsController).build();
	}

	@Test
	public void shouldGetAllTickets() throws Exception {

		final TicketDTO ticket1 = TicketDTO.builder()
				.id(1)
				.title("Ticket 1")
				.description("Ticket description 1")
				.username("user1")
				.build();

		final TicketDTO ticket2 = TicketDTO.builder()
				.id(2)
				.title("Ticket 2")
				.description("Ticket description 2")
				.username("user1")
				.build();

		final List<TicketDTO> tickets = Arrays.asList(ticket1, ticket2);

		when(ticketsService.getAllTickets()).thenReturn(tickets);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tickets");

		mockMvc.perform(request)
				.andExpect(content().json(
						"[{\"id\":1,\"username\":\"user1\",\"title\":\"Ticket 1\",\"description\":\"Ticket description 1\"},{\"id\":2,\"username\":\"user1\",\"title\":\"Ticket 2\",\"description\":\"Ticket description 2\"}]"))
				.andDo(print());
	}
}
