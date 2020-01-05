package com.example.redisTDD2;

import com.example.redisTDD2.controller.MainController;
import com.example.redisTDD2.model.Client;
import com.example.redisTDD2.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RedisTdd2ApplicationTests {

	@Autowired
	private MainController mainController;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void isController(){
		assertThat(mainController, is(notNullValue()));
	}

	@Test
	void testAdd(){
		clientRepository.delete("test");
		Client client = new Client("test", "test", "test");
		clientRepository.add(client);
		assertTrue(clientRepository.isContained(client.getId()));
	}

	@Test
	void testDelete(){
		clientRepository.delete("test");
		Client client = new Client("test", "test", "test");
		clientRepository.add(client);
		clientRepository.delete(client.getId());
		assertFalse(clientRepository.isContained(client.getId()));
	}

	@Test
	void testHome() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("welcome")));
	}

	@Test
	void testAddAndShow() throws Exception{
		this.mockMvc.perform(get("/client/add/test/test/test"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"id\":\"test\",\"name\":\"test\",\"lastName\":\"test\"}")));
	}

	@Test
	void testAll() throws Exception {
		this.mockMvc.perform(get("/client/all"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateAndShow() throws Exception{
		this.mockMvc.perform(get("/client/update/test/test/test"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"id\":\"test\",\"name\":\"test\",\"lastName\":\"test\"}")));
	}

	@Test
	void testDeleteAndShow() throws Exception{
		this.mockMvc.perform(get("/client/delete/test"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("client deleted")));
	}
}
