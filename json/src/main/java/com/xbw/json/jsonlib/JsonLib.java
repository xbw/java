package com.xbw.json.jsonlib;

import com.xbw.json.JsonBean;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 
 * @author xbw
 * 
 *         2019年10月9日
 *
 * @deprecated at Dec, 2010
 */
@Slf4j
public class JsonLib {

	public static void json(JsonBean bean) {
		JSONObject jsonObject = JSONObject.fromObject(bean);// 对象转JSONObject
		log.info("toJson - {}", jsonObject);
		bean = (JsonBean) JSONObject.toBean(jsonObject, JsonBean.class);
		log.info("fromJson - {}", bean);
	}

	public static void json(List<JsonBean> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);// list对象转JSONArray
		log.info("toJson - {}", jsonArray);
	}
}
