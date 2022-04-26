package com.xbw.json.org.json;

import com.xbw.json.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * 
 * @author xbw
 * 
 *         2019年10月9日
 *
 */
@Slf4j
public class OrgJson {

	public static void json(JsonBean bean) {
		log.info("toJson - {}", JSONObject.valueToString(bean));
		log.info("toJson - {}", new JSONObject(bean));
	}

	public static void json(List<JsonBean> list) {
		log.info("toJson - {}", JSONObject.valueToString(list));
		log.info("toJson - {}", new JSONArray(list));
	}
}
