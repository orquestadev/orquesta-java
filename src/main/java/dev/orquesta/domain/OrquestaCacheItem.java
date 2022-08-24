package dev.orquesta.domain;

import org.json.JSONObject;

/***
 * 
 * This class is for storing the CacheItem
 * 
 * @author murtaza
 *
 */
public class OrquestaCacheItem {

	/**
	 * Default constructor
	 */
	public OrquestaCacheItem() {

	}

	/**
	 * All param constructor
	 * 
	 * @param context
	 * @param value
	 * @param time
	 */
	public OrquestaCacheItem(JSONObject context, Object value, long time) {
		super();
		this.context = context;
		this.value = value;
		this.time = time;
	}

	private JSONObject context;

	private Object value;

	private long time;

	/**
	 * 
	 * @return JSONObject
	 */
	public JSONObject getContext() {
		return context;
	}

	/**
	 * 
	 * @param context
	 */
	public void setContext(JSONObject context) {
		this.context = context;
	}

	/**
	 * 
	 * @return Object
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 
	 * @return long
	 */
	public long getTime() {
		return time;
	}

	/**
	 * 
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
