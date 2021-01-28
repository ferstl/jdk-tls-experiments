package com.github.ferstl.jdktls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-selfsigned.p12",
    "test.truststore.location=etc/truststores/selfsigned.p12"
})
class ServerSendsSelfSignedCertificateClientTrustsSelfsignedTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends self-signed certificate, client trusts self-signed certificate -> OK")
  void test() {
    assertEquals("Test", makeRequest());
  }
}
