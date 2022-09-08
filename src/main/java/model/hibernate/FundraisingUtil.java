package model.hibernate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;

public class FundraisingUtil {
	public static String getTableActionStart(HttpServletRequest req, String table, String action) {
		return "\n-------------- command : " + req.getParameter(table) + " " + req.getParameter(action) + " ------- start -------";
	}
	public static String getTableActionEnd(HttpServletRequest req, String table, String action) {
		return "\n-------------- command : " + req.getParameter(table) + " " + req.getParameter(action) + " ------- End -------";
	}
	public static String getImage2byte(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] pic = new byte[fis.available()];
		System.out.println(pic);
		
		fis.read(pic);
		System.out.println(fis);
		fis.close();
		
		// Base64 encodes 3 bytes to 4 characters.
		
		// encode
		String base64str = Base64.getEncoder().encodeToString(pic);
		System.out.println(base64str);
		return base64str;
		
		// decode
//		byte[] fromBase64str = Base64.getDecoder().decode(base64str);
//		FileOutputStream fos = new FileOutputStream(path);
//		fos.write(fromBase64str);
//		fos.flush();
//		fos.close();
	}
}
