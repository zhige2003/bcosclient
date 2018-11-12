package com.block.constants;

import java.util.ResourceBundle;

public class BlockServiceConstants {
	
	public static String BLOCK_KEYSTOREFILENAME = getConfig("block.keyStoreFileName");
	public static String BLOCK_KEYSTOREPASSWORD = getConfig("block.keyStorePassword");
	public static String BLOCK_KEYPASSWORD = getConfig("block.keyPassword");
	
	public static String BS_ACCEPT = getConfig("bs.accept");
	public static String BS_CONTENTTYPE = getConfig("bs.contentType");
	public static String BS_CHARSET = getConfig("bs.charset");

	public static String BS_APPKEY = getConfig("bs.appkey");
	public static String BS_SECRET = getConfig("bs.appsecret");
	public static String BS_AUTHORIZATION = getConfig("bs.authorization");

	public static String METHOD_POST = "POST";

	public static String METHOD_GET = "GET";
	public BlockServiceConstants()  {
		if(BLOCK_KEYSTOREFILENAME == null)
		{
			BLOCK_KEYSTOREFILENAME = "user.jks";
		}
	}
	private static String getConfig(String key) {
		ResourceBundle r = ResourceBundle.getBundle("blockService");
		return r.getString(key);
	}
}