package com.cognizant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest(classes=SpringLearnApplication.class)
@AutoConfigureMockMvc
class SpringLearnApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CountryController countryController;

	@Test
	void contextLoads() {
		Assert.notNull(countryController,"Country object not null");
	}
	
	@Test
	public void testGetCountry() throws Exception{
		ResultActions actions=mockMvc.perform(get("/country"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
	}

	@Test
	public void testGetCountryException() throws Exception {
		ResultActions actions=mockMvc.perform(get("/countries/ina"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country not found"));
	}
	
}
