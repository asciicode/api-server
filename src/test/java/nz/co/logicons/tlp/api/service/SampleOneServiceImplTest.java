package nz.co.logicons.tlp.api.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import nz.co.logicons.tlp.api.test.config.TestConfig;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SampleOneServiceImplTest.class)
@ContextConfiguration(classes = TestConfig.class)
@EnableWebMvc
// @EnableAutoConfiguration
@ComponentScans({
    @ComponentScan(basePackages = "nz.co.logicons.tlp.api"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.config"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.mongo")
})
@ActiveProfiles("test")
public class SampleOneServiceImplTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger(SampleOneServiceImplTest.class);
  @Autowired
  private WebApplicationContext context;

  // @MockBean
  @Autowired
  private SampleOneService sampleOneService;

  @Autowired
  private MongoDatastore datastore;

  @Before
  public void startUp()
  {

  }
  @BeforeEach
  public void init()
  {
    System.out.println("init ");
    LOGGER.info("\n----------------------------ASCII---------------------------");
    for (String name : context.getBeanDefinitionNames())
    {
      LOGGER.info(name);
    }
    LOGGER.info("----------------------------ASCII---------------------------");
    MongoDatastore datastore = (MongoDatastore) context.getBean("mongoDatastore");
    LOGGER.info("MongoDatastore {} ", datastore.hashCode());
  }

  @Test
  public void about()
    throws Exception
  {
    sampleOneService.executeOne();
  }
}
