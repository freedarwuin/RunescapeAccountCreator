package org.medusa.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.medusa.GUI.ProxyGUI;

public class LoadProxies {

static JFileChooser filec = new JFileChooser();
	

	public static String[] proxies;
	public static String content;

	public static void loadProxyFile() throws FileNotFoundException {
		filec.setDialogTitle("Choose proxy .txt");
		if (filec.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = filec.getSelectedFile();
			Scanner input = new Scanner(file);
			if (!file.getName().endsWith(".txt")) {
				System.out.println("Not a .txt file");
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
		} else {
			System.out.println("No file was selected");
		}
	}
	
}
