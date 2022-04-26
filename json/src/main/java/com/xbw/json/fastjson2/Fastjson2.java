package com.xbw.json.fastjson2;

import com.alibaba.fastjson2.JSON;
import com.xbw.json.JsonBean;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xbw
 * <p>
 * 2019年10月9日
 */
@Slf4j
public class Fastjson2 {

    public static void json(JsonBean bean) {
        String json = JSON.toJSONString(bean);
        log.info("toJson - {}", json);
        bean = JSON.parseObject(json, JsonBean.class);
        log.info("fromJson - {}", bean);
    }

    public static void json(List<JsonBean> list) {
        String json = JSON.toJSONString(list);
        log.info("toJson - {}", json);
        list = JSON.parseArray(json, JsonBean.class);
        log.info("fromJson - {}", list);
    }
}
