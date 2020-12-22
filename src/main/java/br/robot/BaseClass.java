package br.robot;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class with common routines
 * @author Rafael Costi <rafaelcosti@outlook.com>
 * @version 1.0.0
 */
public abstract class BaseClass {
	/**
	 * Convert json to java 
	 * object
	 * @param json
	 * @param calzz
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	protected Object jsonToObject(JsonParser json, Class<?> calzz) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValues(json, calzz);
	}
	
	/**
	 * Build parameters log
	 * @param params
	 * @return
	 */
	protected String buildParams(String... params) {
		String p = "{ ";
		if(params != null) {
			boolean is_first = true;
			for (String param : params) {
				if(StringUtils.isEmpty(param)) {
					if(is_first) {
						p = param;
						is_first = false;
					} else {
						p = p + ", " + param;
					}
				}
			}
			p = p + " }";
		}
		return p;
	}
}
