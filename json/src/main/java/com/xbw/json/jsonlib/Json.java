package com.xbw.json.jsonlib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xbw.json.JsonBean;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author xbw
 * 
 *         2019年10月9日
 *
 * @deprecated at Dec, 2010
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

	@SuppressWarnings("static-access")
	public static void json(JsonBean bean) {
		JSONObject jsonObject = JSONObject.fromObject(bean);// 对象转JSONObject
		log.info("toJson - {}", jsonObject);
		bean = (JsonBean) jsonObject.toBean(jsonObject, JsonBean.class);
		log.info("fromJson - {}", bean);
	}

	public static void json(List<JsonBean> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);// list对象转JSONArray
		log.info("toJson - {}", jsonArray);
	}
}
