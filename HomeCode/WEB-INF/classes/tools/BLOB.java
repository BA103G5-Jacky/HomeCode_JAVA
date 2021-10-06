package tools;

import java.io.*;

public  class BLOB {

	
	public static byte[] getPictureByteArray(String path) throws IOException {
		
		File file = new File(path);
		FileInputStream in = new FileInputStream(file);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		in.close();
		
		return baos.toByteArray();
	}
}
