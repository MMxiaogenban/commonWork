package com.nwd.algo.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class AlgoEncryptUtils {
	private static final String defaultCharset = "UTF-8";
	public static String encrypt(String data,String key) {
		return MD5.encryptToUpCase(MD5.encryptToUpCase(data)+key);
	}
	
	public static String encrypt(Map<String, String> paramMap ,String secretKey) {
		return createSign(paramMap, secretKey);
	}
	 public static String createSign(Map<String, String> paramMap ,String secretKey)
	  {
	    StringBuffer sb = new StringBuffer();
	    List<String> keys = new ArrayList<>(paramMap.keySet());
	    Collections.sort(keys);
	    for (int i = 0; i < keys.size(); i++)
	    {
	      String k = keys.get(i) + "";
	      Object val = paramMap.get(k);
	      String v = null;
	      if (val instanceof Long) {
	         v = String.valueOf(val);
	      } else {
	        v = val + "";
	      }

	      if ((null != v) && (!"".equals(v)) && (!"sign".equals(k)) && (!"key".equals(k))) {
	        sb.append(k + "=" + v + "&");
	      }
	    }
	    sb.append("key=" + secretKey);
	    String sign = MD5.MD5Encode(sb.toString(), defaultCharset).toUpperCase();
	    return sign;
	  }
	  
 
	public static class MD5 {
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

//	    public static void main(String[] args) {
//	        String encrypt =  encrypt("admin");
//	        System.out.println(encrypt);
//	        
//	        String encrypt2 = encrypt("companyCode=JIAYIN_JIAJIEcompanyKey=51c93a11c98c1103effe3b926e47be94");
//	        System.out.println(encrypt2);
//	    }
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JSONObject json =(JSONObject) JSONObject.parse( 
				"{\"result\":{\"time\":1542617335056},\"code\":1000,\"requestId\":\"1542617334135o2tvOSwF\",\"message\":\"SUCCESS\",\"deadline\":1857891788000}");
		System.out.println(json.toString());
		System.out.println("==="+AlgoEncryptUtils.encrypt(json.toJSONString(),"123456"));
	}
}
