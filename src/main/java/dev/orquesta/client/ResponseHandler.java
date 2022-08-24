package dev.orquesta.client;

import java.io.IOException;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * This class is responsible for handling the Response come from the API's
 * 
 * @author murtaza
 *
 */
public class ResponseHandler implements HttpClientResponseHandler<JSONObject> {

	private static final Logger LOG = LoggerFactory.getLogger(ResponseHandler.class);

	/**
	 * If status code is not 200 then we are returning null value otherwise
	 * converting it to JSONObject
	 */
	@Override
	public JSONObject handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
		int status = response.getCode();
		LOG.debug("STATUS CODE {}", status);
		if (status == 200) {
			HttpEntity entity = response.getEntity();
			return new JSONObject(EntityUtils.toString(entity));
		} else {
			LOG.error("STATUS CODE IS NOT 200 NEED TO RETURN NULL --- {}", status);
			return null;
		}
	}

}
