package org.medusa.Utils;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

public class GetOSBot {
	
	JFileChooser filec = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	public static String path = "Not set";
	
	public void loadJar() throws FileNotFoundException {
		filec.setDialogTitle("OSBot Jar path");
		if (filec.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = filec.getSelectedFile();
			
			if (!file.getName().endsWith(".jar")) {
				System.out.println("Not a .jar file");
				return;
			}
			
			path = '"' + file.getAbsolutePath() + '"';
			Logger.log("Set osbot jar path: " + path + "\nPress update to see changes.");
			
			
		} else {
			System.out.println("No file was selected");
		}
	}
	
	public static String getPath() {
		return path;
	}
}
