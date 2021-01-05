package com.github.ferstl.jdktls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-expired-leaf-only.p12",
    "test.truststore.location=etc/truststores/expired-leaf.p12"
})
class ServerSendsExpiredLeafCertificateClientTrustsExpiredLeafTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends expired leaf certificate only, client trusts expired leaf certificate -> OK")
  void test() {
    assertEquals("Test", makeRequest());
  }
}
