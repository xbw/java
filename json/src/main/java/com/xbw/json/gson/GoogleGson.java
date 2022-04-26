package com.xbw.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xbw.json.JsonBean;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xbw
 * <p>
 * 2019年10月9日
 */
@Slf4j
public class GoogleGson {

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
        list = gson.fromJson(json, new TypeToken<List<JsonBean>>() {}.getType());
        log.info("fromJson - {}", list);
    }
}