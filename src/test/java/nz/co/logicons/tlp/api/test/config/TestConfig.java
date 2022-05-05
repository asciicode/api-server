package nz.co.logicons.tlp.api.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ActiveProfiles("test")
@ComponentScans({
    @ComponentScan(basePackages = "nz.co.logicons.tlp.api"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.config"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.mongo")
})
public class TestConfig
{
  // @Autowired
  // private MongoTemplate mongoTemplate;

}
