package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

/**
 * @author shoothzj
 */
@Slf4j
public class X509CertUtil {

    private static final CertificateFactory factory;

    static {
        try {
            factory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            log.error("fatal exception, cert factory init failed, {}", ExceptionUtil.getException(e));
            throw new RuntimeException();
        }
    }

    public static boolean isValid(X509Certificate certificate) {
        try {
            certificate.checkValidity();
            return true;
        } catch (CertificateExpiredException e) {
            log.error("cert expire, exception is ", e);
        } catch (CertificateNotYetValidException e) {
            log.error("cert not yet valid ", e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * digitalSignature(0),
     * nonRepudiation(1),
     * keyEncipherment(2),
     * dataEncipherment(3),
     * keyAgreement(4),
     * keyCertSign(5),trueONLYforCAs
     * cRLSign(6),
     * encipherOnly(7),
     * decipherOnly(8)
     * @param certificate x509证书
     * @return 该证书是否为CA证书
     */
    public static boolean isCA(X509Certificate certificate) {
        int basicConstraints = certificate.getBasicConstraints();
        boolean[] usage = certificate.getKeyUsage();
        return basicConstraints != 1 && null != usage && usage.length > 5 && usage[5];
    }

}
