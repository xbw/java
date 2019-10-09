package com.xbw.json.gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xbw.json.JsonBean;

import lombok.extern.slf4j.Slf4j;
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
		// Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(bean);
		log.info("toJson - {}", json);
		bean = gson.fromJson(json, JsonBean.class);
		log.info("fromJson - {}", bean);
	}

	public static void json(List<JsonBean> list) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(list);
		log.info("toJson - {}", json);
		list = gson.fromJson(json, new TypeToken<List<JsonBean>>() {
		}.getType());
		log.info("fromJson - {}", list);
	}
}