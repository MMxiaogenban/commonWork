package com.nwd.algo.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  
 *  * Created with IntelliJ IDEA. 
 *  * User: Sunshine 
 *  * Date: 2016/8/22 16:12 
 *  * Description:  
 *  
 */
public class MD5 {
	private static String byteArrayToHexString(byte[] b)
	{
	    StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	      resultSb.append(byteToHexString(b[i]));
	    }
	    return resultSb.toString();
	  }
	  
	  private static String byteToHexString(byte b)
	  {
	    int n = b;
	    if (n < 0) {
	      n += 256;
	    }
	    int d1 = n / 16;
	    int d2 = n % 16;
	    return hexDigits[d1] + hexDigits[d2];
	  }
	  
	  public static String MD5Encode(String origin, String charsetname)
	  {
	    String resultString = null;
	    try
	    {
	      resultString = new String(origin);
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      if ((charsetname == null) || ("".equals(charsetname))) {
	        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
	      } else {
	        resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
	      }
	    }
	    catch (Exception exception) {}
	    return resultString;
	  }
	  
	  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String encryptToUpCase(String text) {
		byte[] bts;
		try {
			bts = text.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bts_hash = md.digest(bts);
			StringBuffer buf = new StringBuffer();
			for (byte b : bts_hash) {
				buf.append(String.format("%02X", b & 0xff));
			}
			return buf.toString();
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
    public static String encrypt(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static void main(String[] args) {
        String encrypt =  encrypt("admin");
        System.out.println(encrypt);
        
        String encrypt2 = encrypt("companyCode=JIAYIN_JIAJIEcompanyKey=51c93a11c98c1103effe3b926e47be94");
        System.out.println(encrypt2);
    }
}
