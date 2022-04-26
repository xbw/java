package com.xbw.json.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbw.json.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xbw
 * 
 *         2019年10月9日
 */
@Slf4j
public class Jackson {

	public static void json(JsonBean bean) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.writerWithDefaultPrettyPrinter();
		String json = mapper.writeValueAsString(bean);// 对象转json字符串
		log.info("jackson 2.x>>toJson - {}", json);
		bean = mapper.readValue(json, JsonBean.class); // json字符串转对象
		log.info("jackson 2.x>>fromJson - {}", bean);
	}

	public static void json(List<JsonBean> list) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.writerWithDefaultPrettyPrinter();
		String json = mapper.writeValueAsString(list);// 对象转json字符串
		log.info("jackson 2.x>>toJson - {}", json);
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, JsonBean.class);
		list = mapper.readValue(json, javaType); // json字符串转对象
		log.info("jackson 2.x>>fromJson - {}", list);
	}

	public static void jackson(JsonBean bean) throws JsonGenerationException, JsonMappingException, IOException {
		org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String json = mapper.writeValueAsString(bean);// 对象转json字符串
		log.info("jackson 1.x>>toJson - {}", json);
		bean = mapper.readValue(json, JsonBean.class); // json字符串转对象
		log.info("jackson 1.x>>fromJson - {}", bean);
	}

	public static void jackson(List<JsonBean> list) throws JsonGenerationException, JsonMappingException, IOException {
		org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String json = mapper.writeValueAsString(list);// 对象转json字符串
		log.info("jackson 1.x>>toJson - {}", json);
		org.codehaus.jackson.type.JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
				JsonBean.class);
		list = mapper.readValue(json, javaType); // json字符串转对象
		log.info("jackson 1.x>>fromJson - {}", list);
	}
}