package Util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Util {
	public static String readfile(String file) throws IOException {
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		byte[] arr=new byte[bis.available()];
		bis.read(arr);
		String sb=new String(arr);
		bis.close();
		return sb;
	}
	public  static void writefile(String file,String data) throws IOException {
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		byte[] arr=data.getBytes();
		bos.write(arr);
		bos.flush();
		bos.close();
	}
}
