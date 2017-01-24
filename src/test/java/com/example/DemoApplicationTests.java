package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@ContextConfiguration(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getWidget() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/widget").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.color", is("Green")))
				.andExpect(jsonPath("$.x", is(10)))
				.andExpect(jsonPath("$.y", is(7)));
	}

	@Test
	public void getWidgetAsXml() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/widget").accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/xml;charset=UTF-8"))
				.andExpect(xpath("/Widget/color").string("Green"))
				.andExpect(xpath("/Widget/x").string("10"))
				.andExpect(xpath("/Widget/y").string("7"));
	}

	@Test
	public void postWidgetGetXml() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/widget/color/blue").accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/xml;charset=UTF-8"))
				.andExpect(xpath("/Widget/color").string("blue"))
				.andExpect(xpath("/Widget/x").string("10"))
				.andExpect(xpath("/Widget/y").string("7"));
	}

	@Test
	public void postWidgetGetJson() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/widget/color/blue").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.color", is("blue")))
				.andExpect(jsonPath("$.x", is(10)))
				.andExpect(jsonPath("$.y", is(7)));
	}
}
