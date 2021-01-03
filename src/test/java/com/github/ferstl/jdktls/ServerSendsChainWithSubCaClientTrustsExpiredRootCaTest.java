package com.github.ferstl.jdktls;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-with-sub-ca.p12",
    "test.truststore.location=etc/truststores/expired-root-ca.p12"
})
class ServerSendsChainWithSubCaClientTrustsExpiredRootCaTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends Sub CA chain, client trusts expired Root CA -> OK")
  void test() {
    assertEquals("Test", makeRequest());
  }
}
