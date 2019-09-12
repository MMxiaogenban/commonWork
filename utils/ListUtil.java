package com.nwd.algo.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * TakeFirst from list
 * @author yinchunguang
 *
 */
public class ListUtil {
	
	public static List<?> EMPTYLIST = new ArrayList<>(0);

	public static <T> T takeFirst(List<T> list){
		T t = null;
		if (list!=null &&(!list.isEmpty())) {
			t = list.get(0);
		}
		return t;
	}
	
	
	public static <T> List<T> takeTopN(List<T> list,int topN){
		List<T> resultList = null;
		if (list!=null &&(!list.isEmpty())) {
			if (list.size()<=topN) {
				return list;
			}
			resultList = new ArrayList<T>();
					
			for(int i=0;i<topN;i++) {
				T t = list.get(topN);
				resultList.add(t);
			}
		}
		return resultList;
	}
}
