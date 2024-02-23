package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class EncryptionDecryption {
	
	public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
		
		KeyGenerator keyGenerator =  KeyGenerator.getInstance("AES");
		
		keyGenerator.init(256);
		
		return keyGenerator.generateKey();
	}
	
	public static String encrypt(String plaintext , SecretKey key) throws Exception {
		
		Cipher cipher = Cipher.getInstance("AES");
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] plaintextByte = plaintext.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedByte = cipher.doFinal(plaintextByte);
		
		String encryptedDataInString = Base64.getEncoder().encodeToString(encryptedByte);
		
		return encryptedDataInString;

	}
	
	public static String decrypt(String encryptedData ,SecretKey key) throws Exception {
		
		Cipher cipher = Cipher.getInstance("AES");
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] encryptedByteArrayData = Base64.getDecoder().decode(encryptedData);
		byte[] decryptedBytes = cipher.doFinal(encryptedByteArrayData);
		
		return new String(decryptedBytes,StandardCharsets.UTF_8);
	}
	
	public static void main(String args[]) throws Exception {
		
		SecretKey key = generateAESKey();
		
		String plainText = "Test@123";
		
		String encryptedText = encrypt(plainText,key);
		System.out.println("Encrypted :"+encryptedText);
		
		String decryptedText = decrypt(encryptedText,key);
		System.out.println("Decrypted :"+decryptedText);
		
		
	}
	

}
