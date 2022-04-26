package com.xbw.json;

import com.xbw.json.fastjson.Fastjson;
import com.xbw.json.gson.GoogleGson;
import com.xbw.json.jackson.Jackson;
import com.xbw.json.jsonlib.JsonLib;
import com.xbw.json.org.json.OrgJson;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xbw
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class JsonTests {
    private static final AtomicInteger num = new AtomicInteger();
    private static final ArrayList<JsonBean> list = new ArrayList<>();
    private JsonBean jsonBean;

    @BeforeEach
    void setUp() {
        System.out.println(123);
        int i = num.getAndIncrement();
        jsonBean = new JsonBean(i, "bean" + i, new Date(System.currentTimeMillis()));
        list.add(jsonBean);
    }

    @Test
    void fastjson() {
        Fastjson.json(jsonBean);
        Fastjson.json(list);
    }

    @Test
    void gson() {
        GoogleGson.json(jsonBean);
        GoogleGson.json(list);
    }

    @Test
    void jackson() throws IOException {
        Jackson.json(jsonBean);
        Jackson.json(list);
    }

    @Test
    void jackson1() throws IOException {
        Jackson.jackson(jsonBean);
        Jackson.jackson(list);
    }

    @SuppressWarnings("deprecation")
    @Test
    void jsonLib() {
        JsonLib.json(jsonBean);
        JsonLib.json(list);
    }

    @Test
    void orgJson() {
        OrgJson.json(jsonBean);
        OrgJson.json(list);
    }
}
