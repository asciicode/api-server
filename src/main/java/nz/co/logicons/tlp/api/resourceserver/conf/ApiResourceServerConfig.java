package nz.co.logicons.tlp.api.resourceserver.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.header.HeaderWriter;

@Configuration
@EnableResourceServer
public class ApiResourceServerConfig
    extends ResourceServerConfigurerAdapter
{

  @Autowired
  private ResourceServerTokenServices tokenServices;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources)
    throws Exception
  {
    resources.resourceId("api").tokenServices(tokenServices);
  }

  @Override
  public void configure(HttpSecurity http)
    throws Exception
  {
    http
        .antMatcher("/api/**")
        .authorizeRequests()
        // .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read-foo')")
        // .antMatchers(HttpMethod.GET, "/**").permitAll()
        // .antMatchers("/api/**)
        .anyRequest().authenticated()
        .and()
        .headers().addHeaderWriter(new HeaderWriter()
        {
          @Override
          public void writeHeaders(HttpServletRequest request, HttpServletResponse response)
          {
            response.addHeader("Access-Control-Allow-Origin", "*");
            if (request.getMethod().equals("OPTIONS"))
            {
              response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
              response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            }
          }
        });
    http.cors().disable().csrf()
        .disable();
    ;
  }
}
