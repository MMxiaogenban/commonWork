package com.nwd.algo.common.util;

import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

public class Base64Util extends Base64Utils{

	public static boolean isBase64Formated(String input){
		if (input==null) {
			return false;
		}
		try {
			decode(input.getBytes());
			return true;
		}catch (Exception e) {
			return false;
		}

	}

	public static String encodeToUrlSafeString(String src) {

		return encodeToUrlSafeString(src.getBytes(StandardCharsets.UTF_8));
	}

	public static void main(String[] args) throws Exception {

	}

}