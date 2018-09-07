package com.anti_captcha;

import java.net.MalformedURLException;
import java.util.Random;

import org.medusa.Main;
import org.medusa.Utils.LoadProxies;
import org.medusa.Utils.Logger;

public class AccountCreationThread extends Thread {

	@Override
	public void run() {
		 Logger.log("Account thread started");
		if (Main.proxies) {
			Random r = new Random();
			if (LoadProxies.proxies == null) {
				Logger.log("No proxies loaded. Can't start thread.");
			} else {
			int idx = r.nextInt(LoadProxies.proxies.length);
			String proxy = LoadProxies.proxies[idx];
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
	
	public static boolean alive() {
		return activeCount() != 2;
	}
	
	public static int getThreads() {
		return activeCount();
	}
	
}
