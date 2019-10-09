package com.xbw.json.fastjson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
		String json = JSON.toJSONString(bean);
		log.info("toJson - {}", json);
		bean = JSONObject.parseObject(json, JsonBean.class);
		log.info("fromJson - {}", bean);
	}

	public static void json(List<JsonBean> list) {
		String json = JSONArray.toJSONString(list);
		log.info("toJson - {}", json);
		list = JSONArray.parseArray(json, JsonBean.class);
		log.info("fromJson - {}", list);
	}
}
