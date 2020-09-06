package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.print.attribute.standard.Destination;
import javax.swing.JFileChooser;

import windows.ErrorWindow;
import windows.SettingsWindow;

public class Actions {
	private static Actions uniqueInstance;
	private static String destination;
	
	private Actions() {
	}

	public static Actions getInstance() {
		return uniqueInstance;
	}
	
	public static String read(String urlTxT) {
		try {
			FileReader arq = new FileReader(urlTxT);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
		    System.out.println(linha);
		    destination = linha.substring(linha.lastIndexOf("=") + 1);
		    arq.close();
		    return destination;
		}catch(IOException e){
			new ErrorWindow("Error when opening settings", 4);
			return null;
		}
	}
	
	public static void createSettings(String urlTxt) {
		new SettingsWindow().setVisible(true);		
		
	}
	
	public static void checkError(String url, boolean flagNull) {
		if(url == null || url.equals("") || flagNull) {
			System.out.println("URL: "+ url + "Flag: " + flagNull);
			new ErrorWindow("Please, insert a URL",1).setVisible(true);
		}else {
			try {
				CopyFiles.Copy(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String SelectFile(String title) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnD = fileChooser.showOpenDialog(null);
		if(returnD == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return(file.getPath());
		}else {
			return null;
		}
	}
}
