package com.springmicroservices.rest.service;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class CreateSingleArrayService {
	private static final Logger log = LoggerFactory.getLogger(CreateSingleArrayService.class);

	public JSONObject createSingleArray(JSONObject inputArray) throws JSONException {

		Set<Integer> hashset = null;
		JSONObject singleArrayJsonReponse = null;
		JSONArray arraysKeys = null;

		String jsonkey = null;
		String jsonvalue = null;
		String jsonValueBetweenBrac = null;
		String[] jsonArray = null;
		Object value=null;

		try {

			arraysKeys = inputArray.names();
			hashset = new HashSet<>();

			for (int i = 0; i < arraysKeys.length(); i++) {

				jsonkey = arraysKeys.getString(i);
				jsonvalue = inputArray.get(jsonkey).toString();
				jsonValueBetweenBrac = jsonvalue.substring(jsonvalue.indexOf('[') + 1, jsonvalue.indexOf(']'));
				jsonArray = jsonValueBetweenBrac.split(",");

				for (int j = 0; j < jsonArray.length; j++) {
					hashset.add(Integer.parseInt(jsonArray[j]));					
				}
				hashset.forEach(num -> log.info("Hasset value is " + num));
			}
			value = hashset;
			singleArrayJsonReponse = new JSONObject();
			singleArrayJsonReponse.put("array", value);

		} catch (Exception e) {
			log.error("Exception while creating single Array",e);
		}
		log.info("Single Array JSON response is " + singleArrayJsonReponse);
		return singleArrayJsonReponse;
	}
}
