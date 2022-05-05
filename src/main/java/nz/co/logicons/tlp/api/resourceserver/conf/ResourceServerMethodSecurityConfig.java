package nz.co.logicons.tlp.api.resourceserver.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

  // @Override
  // protected MethodSecurityExpressionHandler createExpressionHandler() {
  // return new OAuth2MethodSecurityExpressionHandler();
  // }

    @Bean
    public Module javaTimeModule()
    {
      SimpleModule module = new SimpleModule("simpleModule", new Version(1, 0, 0, null, null, null));
//      module.addSerializer(Foo.class, new FooSerializer());
      return module;
    }
}
