package com.rcronald.ms.jsmproject.swagger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.rcronald.ms.jsmproject.Application;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SwaggerTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SwaggerTest.class);

	private static final String API_URI = "/v2/api-docs";

	@Autowired
	private WebApplicationContext wac;

	@Value("${SWAGGER_TEST_OUTPUT_DIR:#{null}}")
	private String swaggerOutputPath;

	private MockMvc mockMvc;

	@Before
	public void setup() throws IOException {

		Assume.assumeTrue(swaggerOutputPath != null
				&& !swaggerOutputPath.isEmpty());

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		LOGGER.info("The swagger output is {}", swaggerOutputPath);
	}

	@Test
	public void createSwaggerSpec() throws Exception {

		ResultHandler rh = r -> {
			String swaggerJSONAsString = r.getResponse().getContentAsString();
			File swaggerDir = new File(swaggerOutputPath);
			swaggerDir.mkdirs();
			File swaggerFile = new File(swaggerDir, "swagger.json");
			swaggerFile.createNewFile();

			Files.write(Paths.get(swaggerFile.getAbsolutePath()),
					swaggerJSONAsString.getBytes("UTF-8"));
		};

		mockMvc.perform(get(API_URI).accept(MediaType.APPLICATION_JSON)).andDo(
				rh);

	}
}
