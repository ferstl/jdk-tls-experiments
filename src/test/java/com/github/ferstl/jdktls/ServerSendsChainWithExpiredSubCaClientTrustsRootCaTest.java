package com.github.ferstl.jdktls;

import javax.net.ssl.SSLHandshakeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-with-expired-sub-ca.p12",
    "test.truststore.location=etc/truststores/root-ca.p12"
})
class ServerSendsChainWithExpiredSubCaClientTrustsRootCaTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends Sub CA chain with expired Sub CA, client trusts Root CA -> Handshake Failure")
  void test() {
    assertThatThrownBy(this::makeRequest)
        .getCause()
        .isInstanceOf(SSLHandshakeException.class)
        .hasMessageContaining("validity check failed");

    // TODO: Better check for "Caused by: java.security.cert.CertificateExpiredException: NotAfter: Sat Jan 02 01:00:00 CET 2021"
  }
}
