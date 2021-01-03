package com.github.ferstl.jdktls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-leaf-only.p12",
    "test.truststore.location=etc/truststores/sub-ca.p12"
})
class ServerSendsLeafCertificateClientTrustsSubCaTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends leaf certificate only, client trusts Sub CA -> OK")
  void test() {
    assertEquals("Test", makeRequest());
  }
}
