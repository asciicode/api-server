package nz.co.logicons.tlp.api.resourceserver.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ApiResourceServerTokenStoreConfig
{
  @Value("${resource-server.app.client-id}")
  private String clientId;

  @Value("${resource-server.app.client-secret}")
  private String clientSecret;

  @Value("${resource-server.app.check-token-endpoint-url}")
  private String checkTokenEndpointUrl;

  @Primary
  @Bean
  @Profile("!jwttoken")
  public ResourceServerTokenServices remoteTokenServices()
  {
    RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setClientId(clientId);
    tokenService.setClientSecret(clientSecret);
    tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
    return tokenService;
  }

  @Bean
  @Primary
  @Profile("jwttoken")
  public ResourceServerTokenServices jwtTokenServices()
  {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    return defaultTokenServices;
  }

  @Bean
  @Profile("jwttoken")
  public TokenStore tokenStore()
  {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  @Profile("jwttoken")
  public JwtAccessTokenConverter accessTokenConverter()
  {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey("123");
    return converter;
  }
}
