package com.xbw.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@JSONField(format = "yyyy-MM-dd") // fastjson
	private Date date;

	@Override
	public String toString() {
		return "JsonBean (id=" + id + ", name=" + name + ", date=" + new SimpleDateFormat("yyyy-MM-dd").format(date)
				+ ")";
	}
}