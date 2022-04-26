package com.xbw.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xbw.json.JsonBean;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xbw
 * <p>
 * 2019年10月9日
 */
@Slf4j
public class Fastjson {

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
