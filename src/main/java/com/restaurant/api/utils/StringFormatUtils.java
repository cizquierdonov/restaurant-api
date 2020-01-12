package com.restaurant.api.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringFormatUtils {
	
	private static final String ISO_DATE_FORMAT_MASK = "yyyy-MM-dd HH:mm:ss:sssZ"; 

	public static String getTodayISODateFormat() {
    	return new SimpleDateFormat(ISO_DATE_FORMAT_MASK).format(new Date());
    }
	
	public static String getStringHash(String stringToHash, String algorithm) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		}
		
		byte[] hash = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
		String strHash = StringFormatUtils.bytesArrayToHexString(hash);
		
		return strHash;
	}
	
	private static String bytesArrayToHexString(byte[] hash) { 
        BigInteger number = new BigInteger(1, hash);  
  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        while (hexString.length() < 32) {  
            hexString.insert(0, '0');  
        }
  
        return hexString.toString();  
    }

}
