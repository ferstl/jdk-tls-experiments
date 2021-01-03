package com.github.ferstl.jdktls;

import java.nio.file.Path;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class TlsRestTemplateCustomizer implements RestTemplateCustomizer {

  private final Path trustStore;
  private final String trustStorePassword;

  public TlsRestTemplateCustomizer(
      @Value("${test.truststore.location}") Path trustStore,
      @Value("${test.truststore.password}") String trustStorePassword
  ) {
    this.trustStore = trustStore;
    this.trustStorePassword = trustStorePassword;
  }

  @Override
  public void customize(RestTemplate restTemplate) {
    SSLContext sslContext;
    try {
      sslContext = SSLContextBuilder.create()
          .loadTrustMaterial(this.trustStore.toFile(), this.trustStorePassword.toCharArray())
          .build();
    } catch (Exception e) {
      throw new IllegalStateException("Failed to create SSL context", e);
    }

    HttpClient httpClient = HttpClientBuilder.create()
        .setSSLContext(sslContext)
        .build();

    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    restTemplate.setRequestFactory(requestFactory);
  }
}
