package molab.main.java.util;

public class Status {
	
	/**
	 * common states*/
	public static final int SUCCESS = 1;
	public static final int ERROR = -1;
	public static final int ERROR_SESSION_ATTR_LOST = 97;
	public static final int ERROR_SESSION_LOST = 98;
	public static final int ERROR_SYSTEM = 99;
	
	/**
	 * emulator states*/
	public static final int IDLE = 1;
	public static final int BUSY = 2;
	public static final int OTHER = 9;
	
	/**
	 * for login page*/
	public static final int USER_UNACTIVED = 0;
	public static final int USER_NORMAL = 1;
	public static final int USER_NOT_EXIST = 2;
	public static final int PASSWORD_NOT_MATCH = 3;
	
	/**
	 * for register page*/
	public static final int USER_EXIST = -2;
	
	/**
	 * for password reset page*/
	public static final int USERNAME_ERROR = 2;
	public static final int EMAIL_ERROR = 3;
	
	public static final int TRUE = 1;
	public static final int FALSE = 0;
	
	public static final int DEFAULT_LEFT_CLICKS = 10;
	public static final long DEFAULT_EXPIRATION = 32489436216541L;

}