package com.xbw.json.org.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.xbw.json.JsonBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author xbw
 * 
 *         2019年10月9日
 *
 */
@Slf4j
public class Json {

	public static void main(String[] args) {
		List<JsonBean> list = new ArrayList<JsonBean>();
		JsonBean bean = new JsonBean(0, "bean0", new Date(System.currentTimeMillis()));
		json(bean);

		list.add(bean);
		for (int i = 1; i < 3; i++) {
			list.add(new JsonBean(i, "bean" + i, new Date(System.currentTimeMillis())));
		}
		json(list);
	}

	public static void json(JsonBean bean) {
		log.info("toJson - {}", JSONObject.valueToString(bean));
		log.info("toJson - {}", new JSONObject(bean));
	}

	public static void json(List<JsonBean> list) {
		log.info("toJson - {}", JSONObject.valueToString(list));
		log.info("toJson - {}", new JSONArray(list));
	}
}
