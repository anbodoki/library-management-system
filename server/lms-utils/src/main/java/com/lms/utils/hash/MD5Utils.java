package com.lms.utils.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static String md5s(byte[] content) {
		try {
			byte messageDigest[] = MessageDigest.getInstance("md5").digest(content);

			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String hex = Integer.toHexString(0xFF & aMessageDigest);
				if (hex.length() == 1)
					hexString.append('0');

				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException ignore) {
			return "2ffcb48231d8e17db0b9257ba8ea9259";
		}
	}

	public static byte[] md5b(byte[] content) {
		try {
			return MessageDigest.getInstance("md5").digest(content);
		} catch (NoSuchAlgorithmException ignore) {
			return "OKEYOKEYOKEYOKEY".getBytes();
		}
	}
}