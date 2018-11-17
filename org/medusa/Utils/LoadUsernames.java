package org.medusa.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.medusa.GUI.CustomNameGUI;

public class LoadUsernames {

static JFileChooser filec = new JFileChooser();
	

	public static String[] prefix, suffix;
	public static String prefixText, suffixText;
	public static CustomNameGUI nameGUI;

	//Load names from .txt
	public static void loadPrefixFile() throws FileNotFoundException {
		filec.setDialogTitle("Choose names .txt");
		if (filec.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = filec.getSelectedFile();
			Scanner input = new Scanner(file);
			if (!file.getName().endsWith(".txt")) {
				Logger.log("(Names) Not a .txt file");
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
			prefix = split.split("\n");
			prefixText = split;
			CustomNameGUI.txtPrefix.setText(split);
			input.close();
			Logger.log("(Usernames) added prefixes");
		} else {
			Logger.log("(Usernames) No file was selected");
		}
	}
	
	public static void loadSuffixFile() throws FileNotFoundException {
		filec.setDialogTitle("Choose names .txt");
		if (filec.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = filec.getSelectedFile();
			Scanner input = new Scanner(file);
			if (!file.getName().endsWith(".txt")) {
				Logger.log("(Names) Not a .txt file");
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
			suffix = split.split("\n");
			suffixText = split;
			nameGUI.txtSuffix.setText(suffixText);
			input.close();
			Logger.log("(Usernames) added suffixes");
		} else {
			Logger.log("(Usernames) No file was selected");
		}
	}
	
	public static String getPrefix() {
		Random r = new Random();
		int idx = r.nextInt(LoadUsernames.prefix.length);
		return LoadUsernames.prefix[idx];
	}
	
	public static String getSuffix() {
		Random r = new Random();
		int idx = r.nextInt(LoadUsernames.suffix.length);
		return LoadUsernames.suffix[idx];
	}
	
}
