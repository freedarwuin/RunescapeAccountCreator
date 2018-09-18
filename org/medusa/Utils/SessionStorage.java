package org.medusa.Utils;

public class SessionStorage {

	//Ghetto way of checking if JFrame windows are showing
	public boolean logger = false;
	public boolean proxy = false;
	public boolean credits = false;
	
	//Should we sleep between each thread start?
	//Disabling this will make all account threads start at the same time, and spam logger (Faster than with sleep)
	public boolean threadSleep = true;
	
}
