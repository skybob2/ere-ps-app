package health.ere.ps.service.idp.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.LogManager;

import javax.inject.Inject;

import health.ere.ps.exception.connector.ConnectorCardCertificateReadException;
import health.ere.ps.exception.idp.IdpClientException;
import health.ere.ps.exception.idp.IdpException;
import health.ere.ps.exception.idp.IdpJoseException;
import health.ere.ps.exception.idp.crypto.IdpCryptoException;
import health.ere.ps.model.idp.client.IdpTokenResult;
import health.ere.ps.model.idp.crypto.PkiIdentity;
import health.ere.ps.service.connector.certificate.CardCertReadExecutionService;
import health.ere.ps.service.connector.certificate.CardCertificateReaderService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class IdpClientTest {

    @Inject
    IdpClient idpClient;

    @Inject
    CardCertificateReaderService cardCertificateReaderService;

    @Inject
    CardCertReadExecutionService cardCertReadExecutionService;

    @ConfigProperty(name = "idp.client.id")
    String clientId;

    @ConfigProperty(name = "idp.connector.client.system.id")
    String clientSystem;

    @ConfigProperty(name = "idp.connector.workplace.id")
    String workplace;

    @ConfigProperty(name = "idp.connector.card.handle")
    String cardHandle;

    @ConfigProperty(name = "idp.connector.cert.auth.store.file.password")
    String connectorCertAuthPassword;

    @ConfigProperty(name = "idp.base.url")
    String idpBaseUrl;

    String discoveryDocumentUrl;

    @ConfigProperty(name = "idp.auth.request.redirect.url")
    String redirectUrl;

    @BeforeAll
    public static void init() {

        try {
			// https://community.oracle.com/thread/1307033?start=0&tstart=0
			LogManager.getLogManager().readConfiguration(
                IdpClientTest.class
							.getResourceAsStream("/logging.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
    }

    // @Disabled("Disabled until Titus Idp Card Certificate Service API Endpoint Is Fixed By Gematik")
    @Test @Disabled
    public void test_Successful_Idp_Login()
            throws ConnectorCardCertificateReadException, IdpException,
            IdpClientException, IdpCryptoException, IdpJoseException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

        InputStream inStream = CardCertificateReaderService.class.getResourceAsStream("/certs/1-2-ARZT-WaltrautDrombusch01-80276001011699910223-C_SMCB_AUT_R2048_X509.p12");

        /*KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(inStream, "00".toCharArray());  
        
        String alias = ks.aliases().nextElement();
        cardCertificateReaderService.setMockCertificate(((X509Certificate) ks.getCertificate(alias)).getEncoded());*/

        cardCertificateReaderService.setMockCertificate(inStream.readAllBytes());

        InputStream p12Certificate = CardCertificateReaderService.class.getResourceAsStream("/ps_erp_incentergy_01.p12");
        cardCertReadExecutionService.setUpCustomSSLContext(p12Certificate);
        AuthenticatorClient authenticatorClient = new AuthenticatorClient();

        discoveryDocumentUrl = idpBaseUrl + IdpHttpClientService.DISCOVERY_DOCUMENT_URI;

        idpClient.init(clientId, redirectUrl, discoveryDocumentUrl, true);
        idpClient.initializeClient();

        PkiIdentity identity = cardCertificateReaderService.retrieveCardCertIdentity(clientId,
                clientSystem, workplace, cardHandle, connectorCertAuthPassword);

        IdpTokenResult idpTokenResult = idpClient.login(identity);

        Assertions.assertNotNull(idpTokenResult, "Idp Token result present.");
        Assertions.assertNotNull(idpTokenResult.getAccessToken(), "Access Token present");
        Assertions.assertNotNull(idpTokenResult.getIdToken(), "Id Token present");
    }
}
