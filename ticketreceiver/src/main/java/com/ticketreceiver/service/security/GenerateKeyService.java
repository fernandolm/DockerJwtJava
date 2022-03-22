package com.ticketreceiver.service.security;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@NoArgsConstructor
@Slf4j
public class GenerateKeyService {
    public String generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        RSAPublicKey rPubKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey rPriKey = (RSAPrivateKey) kp.getPrivate();

        Base64.Encoder encoder = Base64.getEncoder();

        String publicKey64 = encoder.encodeToString(rPubKey.getEncoded());
        String privateKey64 = encoder.encodeToString(rPriKey.getEncoded());

        log.info(">>> Public Key: {}", publicKey64);
        log.info(">>> Private Key: {}", privateKey64);

        return "Public Key:\n" + publicKey64 + "\n\nPrivate Key:\n" + privateKey64;
    }
}
