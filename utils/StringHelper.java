package com.nwd.algo.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.nwd.algo.common.constant.Constants;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;

/**
 * Created by zj on 2017/9/10.
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
    
    public static String[] removeBlankFromCommaStr(String str) {
    	String[] strs = Optional.ofNullable(str).map((value) -> value.split(Constants.SPLIT_COMMA)).orElse(null);
    	if(ArrayUtil.isEmpty(strs)) {
    		return ArrayUtils.EMPTY_STRING_ARRAY;
    	}
    	return ArrayUtil.removeBlank(strs);
    }

    public static String changeStrArrayToQuery(String[] strs) {
    	if(ArrayUtil.isEmpty(strs)) {
    		return null;
    	}
    	return ArrayUtil.join(strs, Constants.SPLIT_COMMA);
    }
    
    public static String changeStrListToQuery(List<String> strs) {
    	if(CollectionUtil.isEmpty(strs)) {
    		return null;
    	}
    	return CollectionUtil.join(strs, Constants.SPLIT_COMMA);
    }
    
    
    public static String[] strToStrArray(String str) {
    	if(StringUtils.isBlank(str)) {
    		return ArrayUtils.EMPTY_STRING_ARRAY;
    	}
    	String[] strArray = str.replaceAll(Constants.ARRAY_START_CHAR, Constants.STR_EMPTY_CHAR)
    			.replaceAll(Constants.ARRAY_END_CHAR, Constants.STR_EMPTY_CHAR).split(Constants.SPLIT_COMMA);
    	return Arrays.stream(strArray).filter(string -> StringUtils.isNotBlank(string)).toArray(String[]::new);
    }
    
    public static double[] strToDoubleArray(String str) {
    	String[] strArray = strToStrArray(str);
    	if(ArrayUtils.isEmpty(strArray)) {
    		return ArrayUtils.EMPTY_DOUBLE_ARRAY;
    	}
    	return Arrays.stream(strArray).mapToDouble(Double::parseDouble).toArray();
    }

}
