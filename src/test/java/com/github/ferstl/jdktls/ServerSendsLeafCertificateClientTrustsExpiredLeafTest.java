package com.github.ferstl.jdktls;

import javax.net.ssl.SSLHandshakeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-leaf-only.p12",
    "test.truststore.location=etc/truststores/expired-leaf.p12"
})
class ServerSendsLeafCertificateClientTrustsExpiredLeafTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends leaf certificate only, client trusts expired leaf certificate -> Handshake Failure")
  void test() {
    assertThatThrownBy(this::makeRequest)
        .getCause()
        .isInstanceOf(SSLHandshakeException.class)
        .hasMessageContaining("unable to find valid certification path to requested target");
  }
}
