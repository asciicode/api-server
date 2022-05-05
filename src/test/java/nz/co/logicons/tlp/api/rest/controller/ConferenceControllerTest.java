package nz.co.logicons.tlp.api.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ConferenceController.class)
@AutoConfigureMockMvc
@EnableWebMvc
// @EnableAutoConfiguration
public class ConferenceControllerTest
{
  private static final String apiPrefix = API.VERSIONED_PATH + "/distanceLog";
  @Autowired
  private WebApplicationContext context;

  // @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void init()
  {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
    System.out.println("asdfasdf " + context + " " + mockMvc);
  }

  @Test
  public void about()
    throws Exception
  {
    this.mockMvc.perform(get(apiPrefix + "/about").with(SecurityMockMvcRequestPostProcessors.user("Ria")))
        .andExpect(status().isOk());
  }

  @Test
  public void greetings()
    throws Exception
  {
    this.mockMvc.perform(get(apiPrefix + "/greetings").with(SecurityMockMvcRequestPostProcessors.user("Ria")))
        .andExpect(status().isOk())
        .andExpect(content().string("Join us Ria"));
  }

  @Test
  public void greetingsUnauthorized()
    throws Exception
  {
    this.mockMvc.perform(get(apiPrefix + "/greetings"))
        .andExpect(status().isUnauthorized());
  }
}
