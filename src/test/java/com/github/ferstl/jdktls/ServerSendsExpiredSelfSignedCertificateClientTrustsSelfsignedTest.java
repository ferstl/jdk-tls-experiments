package com.github.ferstl.jdktls;

import javax.net.ssl.SSLHandshakeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(properties = {
    "server.ssl.key-store=etc/keystores/localhost-selfsigned-expired.p12",
    "test.truststore.location=etc/truststores/selfsigned.p12"
})
class ServerSendsExpiredSelfSignedCertificateClientTrustsSelfsignedTest extends AbstractWebAppTest {

  @Test
  @DisplayName("Server sends expired self-signed certificate, client trusts self-signed certificate -> Handshake Failure")
  void test() {
    assertThatThrownBy(this::makeRequest)
        .getCause()
        .isInstanceOf(SSLHandshakeException.class)
        .hasMessageContaining("validity check failed")
        .hasStackTraceContaining("java.security.cert.CertificateExpiredException: NotAfter");
  }
}
