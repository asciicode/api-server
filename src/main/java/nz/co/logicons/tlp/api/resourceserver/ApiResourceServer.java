package nz.co.logicons.tlp.api.resourceserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import nz.co.logicons.tlp.core.mongo.MongoDatastore;

@SpringBootApplication
@ComponentScans({
    @ComponentScan(basePackages = "nz.co.logicons.tlp.api"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.config"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.mongo"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.core.rest.controller"),
    @ComponentScan(basePackages = "nz.co.logicons.tlp.api.service.impl")
})
public class ApiResourceServer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApiResourceServer.class);
    public static void main(String[] args) {
      final ConfigurableApplicationContext context = SpringApplication.run(ApiResourceServer.class, args);
      LOGGER.info("\n----------------------------ASCII---------------------------");
      for (String name : context.getBeanDefinitionNames())
      {
        LOGGER.info(name);
      }
      LOGGER.info("----------------------------ASCII---------------------------");
      MongoDatastore datastore = (MongoDatastore) context.getBean("mongoDatastore");
      LOGGER.info("MongoDatastore {} ", datastore.hashCode());
    }
}
