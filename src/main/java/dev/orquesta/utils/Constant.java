package dev.orquesta.utils;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

/***
 * Utils class
 * 
 * @author murtaza
 *
 */
public class Constant {

	private Constant() {
		// Utils class so no need to create object of it.
	}

	public static final String ORQUESTA_EVALUATION_URL = "https://api.orquesta.dev/evaluate";

	public static final String RULE_KEY = "rule_key";

	/**
	 * 
	 * Checking if API key is valid or invalid
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isInvalidKey(String key) {
		return !key.startsWith("RQST");
	}

	/**
	 * Checking if both JSONObject are equal or not
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean isObjectEqual(JSONObject obj1, JSONObject obj2) {
		return obj1.similar(obj2);
	}

	/**
	 * Checking timeout value if difference is less then ttl
	 * 
	 * @param time
	 * @param ttl
	 * @return
	 */
	public static boolean isTimeOut(long time, int ttl) {
		long difference = System.currentTimeMillis() - time;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
		return seconds < ttl;
	}

}
