package by.htp.library.dao.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
	private static Logger logger = LogManager.getLogger(PasswordEncryption.class);
	private final static String MD5 = "MD5";

	public static String takeMD5Function(String input) {
		String hashtext = null;
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return hashtext;
	}
}
