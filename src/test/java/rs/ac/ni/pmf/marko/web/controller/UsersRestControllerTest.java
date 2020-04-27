package rs.ac.ni.pmf.marko.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import rs.ac.ni.pmf.marko.web.TestWebConfiguration;
import rs.ac.ni.pmf.marko.web.controller.impl.UsersRestControllerImpl;
import rs.ac.ni.pmf.marko.web.model.UsersSearchOptions;
import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.service.UsersService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestWebConfiguration.class, UsersRestControllerImpl.class })
public class UsersRestControllerTest {

//	@Autowired
//	private WebApplicationContext webAppContext;

	@Mock
	private UsersService usersService;

	@InjectMocks
	private UsersRestControllerImpl usersRestController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
		mockMvc = MockMvcBuilders.standaloneSetup(usersRestController).build();
	}

	@Test
	public void getUsersApi() throws Exception {
		// Page<UserDTO> usersPage = mock(Page.class);

		final List<UserDTO> users = Arrays.asList(UserDTO.builder().firstName("Marko").username("markom").build());
		final Pageable pageable = PageRequest.of(10, 1);
		final Page<UserDTO> usersPage = new PageImpl<UserDTO>(users, pageable, 100);

		when(usersService.getAllUsers(any(UsersSearchOptions.class))).thenReturn(usersPage);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(
						"{\"content\":[{\"username\":\"markom\",\"firstName\":\"Marko\",\"lastName\":null}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":10,\"pageNumber\":10,\"pageSize\":1,\"paged\":true,\"unpaged\":false},\"totalPages\":100,\"last\":false,\"totalElements\":100,\"size\":1,\"number\":10,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":false,\"numberOfElements\":1,\"empty\":false}"))
				.andDo(print());
	}
}
