package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptionService {

	private static final String SECRET_KEY = "w2fcZkO0vpVOznGSzOVB9g==";

	private static final String ALGORITHM = "AES";

	private static SecretKey secretKey;

	private static Cipher cipher;

	static {
		secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

		try {
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.security.InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String data) throws Exception {
		byte[] encryptedData = cipher.doFinal(data.getBytes());

		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public String decrypt(String encryptedData) throws Exception {
		byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

		return new String(decryptedData, StandardCharsets.UTF_8);
	}

	public static SecretKey generateAESKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error generating AES key", e);
		}

	}

}
