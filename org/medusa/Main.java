package org.medusa;

import com.anti_captcha.Api.NoCaptchaProxyless;
import com.anti_captcha.Helper.DebugHelper;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.medusa.GUI.CustomNameGUI;
import org.medusa.GUI.LoggerGUI;
import org.medusa.GUI.MainGUI;
import org.medusa.GUI.NotificationGUI;
import org.medusa.OSBot.ScriptLaunch;
import org.medusa.Utils.LoadUsernames;
import org.medusa.Utils.Logger;
import org.medusa.Utils.SessionStorage;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

	//Strings which will contain account information 
	public static String emailDomain = "";
	public static String emailPrefix = "";
	public static String passwd = "";

	//Program version
	public static double version = 0.6;
	public static String v = "Alpha";

	//Proxy setting(s)
	public static boolean proxies = false;

	//Custom name setting(s)
	public static boolean customUN = false;

	//Creation statistics and stuff
	public static int currentProgressive = 0;
	public static int currentNumber = 0;
	public static int accountsWanted = 1;
	public static boolean started = false;
	public static String antiCaptchaKey = "";
	public static int accountsCreated = 0;
	public static int completeNumber = 0;

	//Session Stuff
	public static SessionStorage st = new SessionStorage();

	//File Location/Name
	static String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	public static File logFile = new File(timeLog + randomAlphaNumeric(2) + ".txt");

	public static void main(String[] args) throws InterruptedException, MalformedURLException, JSONException {
		//Initializes Main Gui
		MainGUI gui = new MainGUI();
		gui.setVisible(true);

		//Initializes Logger Gui
		LoggerGUI lg = new LoggerGUI();
		st.logger = true;
		lg.setVisible(true);

		//Prints text to logger
		Logger.log("Welcome to Medusa's Account Creator (v" + Main.version + "-" + Main.v + ")");
		Logger.log("Please note that this might not work 100% of the time");
	}


	//Make request to solve captcha. If solved proceed to account creation.
	public static void createAccount(String ip, int port) throws MalformedURLException, InterruptedException {
		Logger.log("Waiting for captcha code... This might take a while...");
		DebugHelper.setVerboseMode(false);
		NoCaptchaProxyless api = new NoCaptchaProxyless();
		api.setClientKey(antiCaptchaKey);
		api.setWebsiteUrl(new URL("https://secure.runescape.com/m=account-creation/create_account"));
		api.setWebsiteKey("6Lcsv3oUAAAAAGFhlKrkRb029OHio098bbeyi_Hv");

		if (!api.createTask()) {
			Logger.log(api.getErrorMessage());
		} else if (!api.waitForResult()) {
			Logger.log("-----------------------");
			Logger.log("Failed to solve captcha");
			completeNumber++;
			if (completeNumber >= accountsWanted) {
				Logger.log("-----------------------");
				Logger.log("Task done");
				NotificationGUI gui = new NotificationGUI("complete", "Done");
				gui.setAlwaysOnTop(true);
				gui.setVisible(true);
				completeNumber = 0;
			}
		} else {
			currentProgressive++;
			if (proxies) {
				createProxyPost(api.getTaskSolution().getGRecaptchaResponse(), ip, port);
			} else {
				createPost(api.getTaskSolution().getGRecaptchaResponse());
			}
		}
	}

	//Create Account without proxy
	public static void createPost(String string) {
		HttpClient httpclient = HttpClients.createDefault();
		try {

			HttpPost httppost = new HttpPost("https://secure.runescape.com/m=account-creation/create_account");

			String email = emailPrefix + currentProgressive + "@" + emailDomain;
			String password = passwd;
			
			Random rand = new Random();
			int day = (1 + rand.nextInt(29));
			int month = (1 + rand.nextInt(11));
			int year = (1965 + rand.nextInt(30));
			
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("email1", email));
			params.add(new BasicNameValuePair("onlyOneEmail", "1"));
			params.add(new BasicNameValuePair("password1", password));
			params.add(new BasicNameValuePair("onlyOnePassword", "1"));
			params.add(new BasicNameValuePair("day", Integer.toString(day)));
			System.out.println("Day: " + day);
			params.add(new BasicNameValuePair("month", Integer.toString(month)));
			System.out.println("Month: " + month);
			params.add(new BasicNameValuePair("year", Integer.toString(year)));
			System.out.println("Year: " + year);
			params.add(new BasicNameValuePair("g-recaptcha-response", string));
			params.add(new BasicNameValuePair("create-submit", "create"));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			System.out.println("Key: " + antiCaptchaKey);

			//Set headers
			httppost.setHeader("Host", "secure.runescape.com");
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT x.y; rv:10.0) Gecko/20100101 Firefox/10.0");
			httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httppost.setHeader("Accept-Language", "en-US,en);q=0.5");
			httppost.setHeader("Accept-Encoding", "gzip, deflate, br");
			httppost.setHeader("Referer", "http://oldschool.runescape.com/");

			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				String getResponseString = readStream(instream);

				completeNumber++;
				try {
					Logger.log("-----------------------");
					Logger.log(email + ":" + password);
					if (getResponseString.contains("Account Created") || getResponseString.length() < 2){
						currentNumber++;
						Logger.log(currentNumber + "/" + accountsWanted + " accounts made.");
						writeFile(email + ":" + password);
						ScriptLaunch.launchScript(st.scriptNameID, st.osbotUsername, st.osbotPassword, email, password);
						if (completeNumber >= accountsWanted) {
							Logger.log("-----------------------");
							Logger.log("Task done");
							NotificationGUI gui = new NotificationGUI("complete", "Done");
							gui.setAlwaysOnTop(true);
							gui.setVisible(true);
							completeNumber = 0;
						}
					} else {
						Logger.log("Creation failed...");
						if (completeNumber >= accountsWanted) {
							Logger.log("-----------------------");
							Logger.log("Task done");
							NotificationGUI gui = new NotificationGUI("complete", "Done");
							gui.setAlwaysOnTop(true);
							gui.setVisible(true);
							completeNumber = 0;
						}
					}
				} finally {
					instream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Create account with proxy
	public static void createProxyPost(String gresponse, String ip, int port) {
		try {
			HttpHost proxy = new HttpHost(ip, port);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			CloseableHttpClient httpclient2 = HttpClients.custom().setRoutePlanner(routePlanner).build();

			HttpPost httppost = new HttpPost("https://secure.runescape.com/m=account-creation/create_account");

			String email = emailPrefix + currentProgressive + "@" + emailDomain;
			String password = passwd;
			
			Random rand = new Random();
			int day = (1 + rand.nextInt(29));
			int month = (1 + rand.nextInt(11));
			int year = (1965 + rand.nextInt(30));
			
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("email1", email));
			params.add(new BasicNameValuePair("onlyOneEmail", "1"));
			params.add(new BasicNameValuePair("password1", password));
			params.add(new BasicNameValuePair("onlyOnePassword", "1"));
			params.add(new BasicNameValuePair("day", Integer.toString(day)));
			System.out.println("Day: " + day);
			params.add(new BasicNameValuePair("month", Integer.toString(month)));
			System.out.println("Month: " + month);
			params.add(new BasicNameValuePair("year", Integer.toString(year)));
			System.out.println("Year: " + year);
			params.add(new BasicNameValuePair("g-recaptcha-response", gresponse));
			params.add(new BasicNameValuePair("create-submit", "create"));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			System.out.println("Key: " + antiCaptchaKey);

			//Set headers
			httppost.setHeader("Host", "secure.runescape.com");
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT x.y; rv:10.0) Gecko/20100101 Firefox/10.0");
			httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httppost.setHeader("Accept-Language", "en-US,en);q=0.5");
			httppost.setHeader("Accept-Encoding", "gzip, deflate, br");
			httppost.setHeader("Referer", "http://oldschool.runescape.com/");

			//Execute and get the response.
			try {
				HttpResponse response = httpclient2.execute(httppost);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String getResponseString = readStream(instream);

					completeNumber++;
					try {
						Logger.log("-----------------------");
						Logger.log(email + ":" + password + ":" + "(Proxy: " + ip + ":" + port + ")");
						if (getResponseString.contains("Account Created") || getResponseString.length() < 2){
							currentNumber++;
							Logger.log(currentNumber + "/" + accountsWanted + " accounts made.");
							writeFile(email + ":" + password);
							ScriptLaunch.launchScriptProxy(st.scriptNameID, st.osbotUsername, st.osbotPassword, email, password, ip, port);
							if (completeNumber >= accountsWanted) {
								Logger.log("-----------------------");
								Logger.log("Task done");
								NotificationGUI gui = new NotificationGUI("complete", "Done");
								gui.setAlwaysOnTop(true);
								gui.setVisible(true);
								completeNumber = 0;
							}
						} else {
							Logger.log("Creation failed...");
						}
					} finally {
						instream.close();
					}
				}
			} catch (IOException e) {
				completeNumber++;
				Logger.log("Failed to connect to proxy");
				if (completeNumber >= accountsWanted) {
					Logger.log("-----------------------");
					Logger.log("Task done");
					NotificationGUI gui = new NotificationGUI("complete", "Done");
					gui.setAlwaysOnTop(true);
					gui.setVisible(true);
					completeNumber = 0;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Reader
	static String readStream(InputStream stream) throws IOException {
		try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = stream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			return result.toString("UTF-8");
		}
	}

	//Random alphanum String
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}


	//Creates/Writes to account file
	public static void writeFile(String account) {
		BufferedWriter writer = null;
		if (logFile.exists()) {
			try(FileWriter fw = new FileWriter(logFile, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
			{
				out.println(account);
			} catch (IOException e) {
			}
		} else {
			try {

				Logger.log(logFile.getCanonicalPath());

				writer = new BufferedWriter(new FileWriter(logFile));
				writer.write(account + "\r\n");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
