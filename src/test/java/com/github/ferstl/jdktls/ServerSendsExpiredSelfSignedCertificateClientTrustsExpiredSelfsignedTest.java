package com.github.ferstl.jdktls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-selfsigned-expired.p12",
    "test.truststore.location=etc/truststores/selfsigned-expired.p12"
})
class ServerSendsExpiredSelfSignedCertificateClientTrustsExpiredSelfsignedTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends expired self-signed certificate, client trusts expired self-signed certificate -> OK")
  void test() {
    assertEquals("Test", makeRequest());
  }
}
