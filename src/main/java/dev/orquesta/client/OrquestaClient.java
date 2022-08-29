package dev.orquesta.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.orquesta.domain.OrquestaCacheItem;
import dev.orquesta.domain.OrquestaOptions;
import dev.orquesta.exception.OrquestaIncorrectAPIException;
import dev.orquesta.utils.Constant;

/**
 * This class is responsible for making query to the API's
 * 
 * @author murtaza
 *
 */
public class OrquestaClient {

	private static final Logger LOG = LoggerFactory.getLogger(OrquestaClient.class);

	private OrquestaOptions orquestaOptions;

	/**
	 * Cache variable to store the value in cache
	 */
	private static Map<String, OrquestaCacheItem> cacheItem = new HashMap<String, OrquestaCacheItem>();

	/**
	 * 
	 * @param orquestaOptions We need to pass OrquestaOptions to create
	 *                        OrquestaClient
	 */
	public OrquestaClient(OrquestaOptions orquestaOptions) {
		this.orquestaOptions = orquestaOptions;
	}

	/**
	 * 
	 * This method is for calling query String API's
	 * 
	 * @param ruleKey
	 * @param defaultValue
	 * @param context
	 * @param cache
	 * @return
	 */
	public String query(String ruleKey, String defaultValue, JSONObject context, Boolean cache) {

		Object cacheValue = checkCache(ruleKey, context, cache);

		if (cacheValue != null) {
			return (String) cacheValue;
		} else {
			try {
				JSONObject response = queryRequest(Constant.RULE_KEY, ruleKey, context);
				if (response == null) {
					return defaultValue;
				} else {
					String rtrnValue = response.getString(ruleKey);
					if (cache) {
						cacheItem.put(ruleKey, new OrquestaCacheItem(context, rtrnValue, System.currentTimeMillis()));
					}
					return rtrnValue;
				}
			} catch (Exception e) {
				LOG.error("Error while quering string value {}", e.getMessage());
				return defaultValue;
			}
		}
	}

	/**
	 * 
	 * This method is for calling query Integer API's
	 * 
	 * @param ruleKey
	 * @param defaultValue
	 * @param context
	 * @param cache
	 * @return
	 */
	public Number query(String ruleKey, Number defaultValue, JSONObject context, Boolean cache) {

		Object cacheValue = checkCache(ruleKey, context, cache);

		if (cacheValue != null) {
			return (Number) cacheValue;
		} else {
			try {
				JSONObject response = queryRequest(Constant.RULE_KEY, ruleKey, context);
				if (response == null) {
					return defaultValue;
				} else {
					Number rtrnValue = response.getNumber(ruleKey);
					//int rtrnValue = response.getInt(ruleKey);
					if (cache) {
						cacheItem.put(ruleKey, new OrquestaCacheItem(context, rtrnValue, System.currentTimeMillis()));
					}
					return rtrnValue;
				}
			} catch (Exception e) {
				LOG.error("Error while quering integer value {}", e.getMessage());
				return defaultValue;
			}
		}

	}

	/**
	 * 
	 * This method is for calling query Boolean API's
	 * 
	 * @param ruleKey
	 * @param defaultValue
	 * @param context
	 * @param cache
	 * @return
	 */
	public Boolean query(String ruleKey, Boolean defaultValue, JSONObject context, Boolean cache) {

		Object cacheValue = checkCache(ruleKey, context, cache);

		if (cacheValue != null) {
			return (Boolean) cacheValue;
		} else {
			try {
				JSONObject response = queryRequest(Constant.RULE_KEY, ruleKey, context);
				if (response == null) {
					return defaultValue;
				} else {
					Boolean rtrnValue = response.getBoolean(ruleKey);
					if (cache) {
						cacheItem.put(ruleKey, new OrquestaCacheItem(context, rtrnValue, System.currentTimeMillis()));
					}
					return rtrnValue;
				}
			} catch (Exception e) {
				LOG.error("Error while quering boolean value {}", e.getMessage());
				return defaultValue;
			}
		}
	}

	/**
	 * This method is for calling query List API's
	 * 
	 * @param ruleKey
	 * @param defaultValue
	 * @param context
	 * @param cache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> query(String ruleKey, List<String> defaultValue, JSONObject context, Boolean cache) {

		Object cacheValue = checkCache(ruleKey, context, cache);
		if (cacheValue != null) {
			return (List<String>) cacheValue;
		} else {
			try {
				JSONObject response = queryRequest(Constant.RULE_KEY, ruleKey, context);
				if (response == null) {
					return defaultValue;
				} else {
					JSONArray jsonArray = response.getJSONArray(ruleKey);
					List<String> list = new ArrayList<String>();
					jsonArray.forEach(item -> {
						list.add((String) item);
					});
					if (cache) {
						cacheItem.put(ruleKey, new OrquestaCacheItem(context, list, System.currentTimeMillis()));
					}
					return list;
				}
			} catch (Exception e) {
				LOG.error("Error while quering list of string value {}", e.getMessage());
				return defaultValue;
			}
		}
	}

	/**
	 * This method is for calling query JSONObject API's
	 * 
	 * @param ruleKey
	 * @param defaultValue
	 * @param context
	 * @param cache
	 * @return
	 */
	public JSONObject query(String ruleKey, JSONObject defaultValue, JSONObject context, Boolean cache) {

		Object cacheValue = checkCache(ruleKey, context, cache);

		if (cacheValue != null) {
			return (JSONObject) cacheValue;
		} else {
			try {
				JSONObject response = queryRequest(Constant.RULE_KEY, ruleKey, context);
				if (response == null) {
					return defaultValue;
				} else {
					if (cache) {
						cacheItem.put(ruleKey, new OrquestaCacheItem(context, response, System.currentTimeMillis()));
					}
					return response;
				}
			} catch (Exception e) {
				LOG.error("Error while quering json object value {}", e.getMessage());
				return defaultValue;
			}
		}

	}

	/**
	 * 
	 * This method is for calling query domain API's
	 * 
	 * @param domain
	 * @param context
	 * @param cache
	 * @return
	 */
	public JSONObject queryDomain(String domain, JSONObject context, Boolean cache) {

		JSONObject fallbackValue = new JSONObject();


		Object cacheValue = checkCache(domain, context, cache);
		if (cacheValue != null) {
			return (JSONObject) cacheValue;
		} else {
			try {
				JSONObject response = queryRequest("domain", domain, context);
				if (response == null) {
					return fallbackValue;
				} else {
					if (cache) {
						cacheItem.put(domain, new OrquestaCacheItem(context, response, System.currentTimeMillis()));
					}
					return response;
				}
			} catch (Exception e) {
				LOG.error("Error while quering domain value {}", e.getMessage());
				return fallbackValue;
			}

		}
	}

	/**
	 * This method is for building request and also it will check for the API key is
	 * valid or invalid
	 * 
	 * @return
	 * @throws OrquestaIncorrectAPIException
	 */
	private HttpPost buildRequest() throws OrquestaIncorrectAPIException {
		if (orquestaOptions.getApiKey() == null || Constant.isInvalidKey(orquestaOptions.getApiKey())) {
			throw new OrquestaIncorrectAPIException("Invalid API KEY");
		}
		HttpPost httpPost = new HttpPost(Constant.ORQUESTA_EVALUATION_URL);
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + orquestaOptions.getApiKey());
		return httpPost;
	}

	/**
	 * 
	 * This method is for creating request body and executing the API's
	 * 
	 * @param key
	 * @param value
	 * @param context
	 * @return
	 * @throws IOException
	 * @throws OrquestaIncorrectAPIException
	 */
	private JSONObject queryRequest(String key, String value, JSONObject context)
			throws IOException, OrquestaIncorrectAPIException {
		HttpPost buildRequest = buildRequest();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		jsonObject.put("context", context);
		buildRequest.setEntity(new StringEntity(jsonObject.toString()));
		CloseableHttpClient createDefault = HttpClients.createDefault();
		ResponseHandler responseHandler = new ResponseHandler();
		LOG.debug("------Calling Web services START----");
		return createDefault.execute(buildRequest, responseHandler);
	}

	/**
	 * This method is used for check cache value by comparing it's key from cache
	 * map and also comparing context from map and comparing ttl value
	 * 
	 * @param key
	 * @param context
	 * @param cache
	 * @return
	 */
	private Object checkCache(String key, JSONObject context, Boolean cache) {
		LOG.debug("Checking cache value, cache:{}", cache);
		Object cacheValue = null;
		if (cache) {
			OrquestaCacheItem orquestaCacheItem = cacheItem.get(key);
			if (orquestaCacheItem != null && Constant.isObjectEqual(orquestaCacheItem.getContext(), context)
					&& Constant.isTimeOut(orquestaCacheItem.getTime(), orquestaOptions.getTtl())) {
				LOG.debug("Cache value found need to take value from it");
				cacheValue = orquestaCacheItem.getValue();
			}
		}
		return cacheValue;
	}

}
