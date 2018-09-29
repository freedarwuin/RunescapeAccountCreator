package org.medusa.OSBot;

import java.io.IOException;

import org.medusa.Main;
import org.medusa.Utils.GetOSBot;
import org.medusa.Utils.Logger;

public class ScriptLaunch {

	/**
	 * 
	
	 */
	
	public static void launchScript(String scriptIDName, String osbotUser, String osbotPassword, String accountLogin, String accountPassword) {
		if (!Main.st.useScript) {
			return;
		}
		String cmd = "cmd.exe /c start java -jar " + GetOSBot.getPath() + " -login " + osbotUser + ":" + osbotPassword + " -bot " + accountLogin + ":" + accountPassword + ":1234 -script " + scriptIDName + ":" + Main.st.getScriptParams();
		System.out.println(cmd);
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(cmd);
			Logger.log("One account launched using input: " + cmd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void launchScriptProxy(String scriptIDName, String osbotUser, String osbotPassword, String accountLogin, String accountPassword, String ip, int port) {
		if (!Main.st.useScript) {
			return;
		}
		if (!Main.st.useScript) {
			return;
		}
		String cmd = "cmd.exe /c start java -jar " + GetOSBot.getPath() + " -proxy " + ip + ":" + port + " -login " + osbotUser + ":" + osbotPassword + " -bot " + accountLogin + ":" + accountPassword + ":1234 -script " + scriptIDName + ":" + Main.st.getScriptParams();
		System.out.println(cmd);
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(cmd);
			Logger.log("One account launched using input: " + cmd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
