package com.niwodai.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

public class JsonUtil {
	private static SerializeConfig serializeConfig =  new SerializeConfig();
	static{
		serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;;
	}
	public static String objectToJson(Object o) {
		return JSON.toJSONString(o,serializeConfig);
	}
	

}
