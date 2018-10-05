package com.anti_captcha;

import java.net.MalformedURLException;
import java.util.Random;

import org.medusa.Main;
import org.medusa.Utils.LoadProxies;
import org.medusa.Utils.Logger;

public class AccountCreationThread extends Thread {

	/**
	 * Messy code. Made it "late at night". It works, so not sure it'll be optimized anytime soon.
	 */
	
	@Override
	public void run() {
		 Logger.log("Account thread started");
		if (Main.proxies) {
			if (LoadProxies.proxies == null) {
				Logger.log("No proxies loaded. Can't start thread.");
			} else {
			String proxy = LoadProxies.getProxy();
			String[] split = proxy.split(":");
			try {
				Main.createAccount(split[0], Integer.parseInt(split[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		} else {
			try {
				Main.createAccount("0", 0);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Checks if creation threads are running
	public static boolean alive() {
		return activeCount() != 2;
	}
	
	//Gets amount of active threads
	public static int getThreads() {
		return activeCount();
	}
	
}
