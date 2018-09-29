package org.medusa.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.medusa.GUI.ProxyGUI;

public class LoadProxies {

static JFileChooser filec = new JFileChooser();
	

	public static String[] proxies;
	public static String content;

	//Load proxies from .txt
	public static void loadProxyFile() throws FileNotFoundException {
		filec.setDialogTitle("Choose proxy .txt");
		if (filec.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = filec.getSelectedFile();
			Scanner input = new Scanner(file);
			if (!file.getName().endsWith(".txt")) {
				Logger.log("(Proxies) Not a .txt file");
				input.close();
				return;
			}
			String split = "";
			 while(input.hasNextLine()) {
				 if (split == "") {
					split = input.nextLine();
				 } else {
	                split = split + "\n" + input.nextLine();
				 }
	            }   
			proxies = split.split("\n");
			content = split;
			ProxyGUI.textArea.setText(split);
			input.close();
			Logger.log("(Proxies) added proxies");
		} else {
			Logger.log("(Proxies) No file was selected");
		}
	}
	
	public static String getProxy() {
		
		Random r = new Random();
		int idx = r.nextInt(LoadProxies.proxies.length);
		return LoadProxies.proxies[idx];
  }
	
}
