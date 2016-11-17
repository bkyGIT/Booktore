package com.atguigu.bookstore.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.User;

/*
 * 將參數直接封裝為對象
 */
public class WEBUtils {

	public static <T>T param2Bean(HttpServletRequest request, T t) {
		
		Map map = request.getParameterMap();
		
		try {
			BeanUtils.populate(t, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return t;
	}

}
