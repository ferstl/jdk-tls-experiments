package com.github.ferstl.jdktls;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class TestConfiguration {

  @Bean
  public ClientHttpRequestFactory requestFactory() {
    return new HttpComponentsClientHttpRequestFactory();
  }
}
