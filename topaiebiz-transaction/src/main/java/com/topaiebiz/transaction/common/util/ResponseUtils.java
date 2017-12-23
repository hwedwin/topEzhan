package com.topaiebiz.transaction.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description 将对应的文本以对应的格式发送&对应一些比较好的java方法
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年8月31日 下午3:11:03
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public final class ResponseUtils {

	private static String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);

	/**
	 * 
	 * Description 发送json。使用UTF-8编码。
	 * 
	 * Author zhushuyong
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param jsonText
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, String jsonText) {
		render(response, "application/json;charset=UTF-8", jsonText);
	}

	/**
	 * 
	 * Description 发送文本，使用UTF-8编码
	 * 
	 * Author zhushuyong
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * 
	 * 发送内容。使用UTF-8编码。
	 * 
	 * Author zhushuyong
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param contentType
	 *            类型
	 * @param text
	 *            对应的文本
	 */
	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * Description 比较两个对象属性字段内容的变化
	 * 
	 * Author zhushuyong
	 * 
	 * @param beforeObject
	 *            修改前的对象
	 * @param afterObject
	 *            修改后的对象
	 * @return beforeObject,afterObject相同对象则返回修改前后的字段值 否则返回空串
	 * @throws Exception
	 */
	public static String DiffObject(Object beforeObject, Object afterObject) throws Exception {
		if (!beforeObject.getClass().equals(afterObject.getClass()))
			return "";
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_PATTERN);
		StringBuffer resultBuffer = new StringBuffer();
		Class<?> clazz = beforeObject.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.contains("get")) {
				Object beforeValue = method.invoke(beforeObject);
				Object afterValue = method.invoke(afterObject);
				if (!beforeValue.equals(afterValue)) {
					StringBuilder methodNameBuilder = new StringBuilder(methodName.substring(3));
					methodNameBuilder.setCharAt(0, Character.toLowerCase(methodNameBuilder.charAt(0)));
					String returnType = method.getReturnType().getSimpleName();
					resultBuffer.append("字段: ").append(methodNameBuilder.toString()).append(":").append(returnType)
							.append(" ");
					if (returnType.equalsIgnoreCase("Date")) {
						resultBuffer.append(" before: ").append(format.format(beforeValue)).append(" after: ")
								.append(format.format(afterValue)).append(" ");
					} else {
						resultBuffer.append(" before: ").append(beforeValue).append(" after: ").append(afterValue)
								.append(" ");
					}
				}
			}
		}
		return resultBuffer.toString();
	}
	
	public static Integer pageCurrent(Integer current) {
		if(current==null) {
			current=0;
		}
		return current;	
	}
	
	public static Integer pageSize(Integer size) {
		if(size==null) {
			size=15;
		}
		return size;	
	}

	/**
	 * 
	 * Description 是否是正整数  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param bot
	 * @return
	 */
	public static boolean isMatches(String bot) {
		boolean flag = false;
		try {
			String regex = "^[1-9]+[0-9]*$";
			// ^[1-9]+\\d*$
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(bot);
			if (m.find()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) throws Exception {
		//isMatches("0");
		// SimpleDateFormat format = new SimpleDateFormat(FORMAT_PATTERN);
		// Bird b1 = new Bird();
		// b1.setName("Bird11111");
		// b1.setAge(20);
		// b1.setBirthday(format.parse("2016-10-11 23:45:43"));
		// b1.setIsOld(true);
		// Bird b2 = new Bird();
		// b2.setName("Bird2222222");
		// b2.setAge(50);
		// b2.setBirthday(format.parse("2014-10-11 23:45:43"));
		// b2.setIsOld(true);
		// Cat cat = new Cat("cat");
		// System.out.println(DiffObject(b1, b2));
		// System.out.println(DiffObject(b1, cat));
	}

}
