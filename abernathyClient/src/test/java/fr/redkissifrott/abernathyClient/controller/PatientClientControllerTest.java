package fr.redkissifrott.abernathyClient.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientClientControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Test
	public void testGetEmployees() throws Exception {
		mockMvc.perform(get("/list")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("list"));
		// .andExpect((ResultMatcher) content()
		// .string(containsString("Laurent")));
	}

}
