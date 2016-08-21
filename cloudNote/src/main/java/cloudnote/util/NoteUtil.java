package cloudnote.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.security.MD5Encoder;

public class NoteUtil implements Serializable{
	public static String md5Encode(String src) throws NoSuchAlgorithmException{
		String s=null;
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] b=md.digest(src.getBytes());
		s=Base64.encodeBase64String(b);
		return s;
	}
	public static String getId(){
		UUID uu=UUID.randomUUID();
		String id=uu.toString();
		return id;
	}
}
