package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import windows.errorWindow;


public class CopyFiles {

	private static CopyFiles uniqueInstance;

	private CopyFiles() {
	}

	public static CopyFiles getInstance() {
		return uniqueInstance;
	}

	
	public static void Copy(String source) throws IOException {
		   BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		   File src = new File(source);
		   String destination = "D:\\Pastas\\Backups";
		   String folder = "D:\\Pastas\\Backups\\" + source.substring(source.lastIndexOf("\\") + 1);
		   
		   File newFolder = new File(folder);
		   System.out.println(newFolder);
		   
		   if (!newFolder.exists()) {
			   newFolder.mkdir();
			}else {
				char lastChar = folder.charAt(folder.length()-1);
				
				if(Character.isDigit(lastChar)) {
					int oneMore = lastChar;
					oneMore++;
					folder = folder.substring(0, folder.length()-1) + oneMore;
				}else {
					folder = folder + "2";
				}
				
				newFolder = new File(folder);
				newFolder.mkdir();
				System.out.println(newFolder);
			}
		   
		   File dst = new File(folder); 
		   copyDirectory(src, dst);
	}
	
	private static void copyDirectory(File srcPath, File dstPath) throws IOException {

		if (srcPath.isDirectory()) {
			if (!dstPath.exists()) {
				dstPath.mkdir();
			}

			String files[] = srcPath.list();

			for (int i = 0; i < files.length; i++) {
				copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i]));
			}
		}else{
			if (!srcPath.exists()) {
				new errorWindow("URL does not exist",2).setVisible(true);
			}
			else{
				InputStream in = new FileInputStream(srcPath);
				OutputStream out = new FileOutputStream(dstPath);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			}
		}

	}
}
