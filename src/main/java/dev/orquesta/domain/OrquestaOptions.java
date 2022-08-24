package dev.orquesta.domain;

/**
 * This class should be initialize first before calling to OrquestaClient
 * 
 * @author murtaza
 *
 */
public class OrquestaOptions {

	/**
	 * @param apiKey
	 * @param ttl    IF TTL value is not provided then it will be set to 3600
	 */

	public OrquestaOptions(String apiKey, Integer ttl) {
		super();
		this.apiKey = apiKey;
		if (ttl == null) {
			this.ttl = 3600;
		} else {
			this.ttl = ttl;
		}
	}

	/*
	 * API Key string
	 */
	private String apiKey;

	/**
	 * TTL value
	 */
	private Integer ttl;

	/**
	 * @return APIKEY
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey Setting API KEY
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return TTL value
	 */

	public Integer getTtl() {
		return ttl;
	}

	/**
	 * 
	 * @param ttl Setting TTL VALUE
	 */
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

}
