package org.medusa.Utils;

import org.medusa.Main;
import org.medusa.GUI.LoggerGUI;

public class Logger {

	public static void log(String l) {
		System.out.println(l);
		if (LoggerGUI.textArea != null) {
			if (LoggerGUI.textArea.getText().isEmpty()) {
				LoggerGUI.textArea.setText(l);
			} else {
				LoggerGUI.textArea.append("\n" + l);
			}
		}
	}
	
}
