package com.nwd.algo.common.util;

import java.util.Collection;
import java.util.Map;

import com.nwd.algo.common.exception.BaseException;

import cn.hutool.core.map.MapUtil;

public class Assert {

	public static void notNull(Object value,String msg,int code){
		if (value == null) {
			throw new BaseException(msg,code);
		}
	}
	public static void notEmpty(Collection<?> value,String msg,int code){
		if (value == null||CollectionUtils.isEmpty(value)) {
			throw new BaseException(msg,code);
		}
	}
	 
	public static void notEmpty(Map<?,?> value,String msg,int code){
		if (value == null||MapUtil.isEmpty(value)) {
			throw new BaseException(msg,code);
		}
	}
}
