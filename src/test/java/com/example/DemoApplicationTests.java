package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@ContextConfiguration(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {
	private final static String APPLICATION_XML_UTF8 = "application/xml;charset=UTF-8";

	// Use http://www.freeformatter.com/xpath-tester.html#ad-output to test xpath

	@Autowired
	private MockMvc mvc;

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void getWidget() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/widget").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$..[?(@.color==\"Magenta\")]", hasSize(1)));
	}

	@Test
	public void getWidgetAsXml() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/widget").accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_XML_UTF8))
				.andExpect(xpath("count(//color[text()=\"Cyan\"])").string("1"));
	}

	@Test
	public void postWidgetGetXml() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/widget/color/blue").accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_XML_UTF8))
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

	@Test
	public void postWidgetAsJsonGetJson() throws Exception {
		Widget w = new Widget("blue", 10, 7);

		mvc.perform(MockMvcRequestBuilders.post("/widget/position")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(w))
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.color", is("blue")))
				.andExpect(jsonPath("$.x", is(10)))
				.andExpect(jsonPath("$.y", is(7)));
	}

	@Test
	public void postWidgetAsXmlGetXml() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/widget/position")
						.accept(MediaType.APPLICATION_XML)
						.contentType(MediaType.APPLICATION_XML)
						.content("<Widget><color>blue</color><x>42</x><y>43</y></Widget>")
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_XML_UTF8))
				.andExpect(xpath("/Widget/color").string("blue"))
				.andExpect(xpath("/Widget/x").string("42"))
				.andExpect(xpath("/Widget/y").string("43"));

	}
}
