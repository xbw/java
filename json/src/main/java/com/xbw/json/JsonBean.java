package com.xbw.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author xbw
 * 
 *         2019年10月9日
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonBean {
	private Integer id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // jackson 2.x
	@com.alibaba.fastjson.annotation.JSONField(format = "yyyy-MM-dd") // fastjson
	@com.alibaba.fastjson2.annotation.JSONField(format = "yyyy-MM-dd") // fastjson2
	private Date date;

	@Override
	public String toString() {
		return "JsonBean (id=" + id + ", name=" + name + ", date=" + new SimpleDateFormat("yyyy-MM-dd").format(date)
				+ ")";
	}
}