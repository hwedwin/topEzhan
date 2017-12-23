package com.topaiebiz.sms.dahantc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;

	public static Calendar calendar = Calendar.getInstance();

	/**
	 * 格式化date
	 * 
	 * @return
	 */
	public static Date nowFormatDate(String formatStr) {
		Date retValue = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
		try {
			retValue = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 当前时间转成字符串 格式为： 2016-01-18 19:25:40
	 * 
	 * @return
	 */
	public static String nowTimeToString() {
		Date now = new Date();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
	}

	/**
	 * 当前时间转成字符串 格式为： 2016-01-18 19:25:40
	 * 
	 * @return
	 */
	public static String nowTimeToString2() {
		Date now = new Date();
		return new SimpleDateFormat("yyyyMMddHHmmss").format(now);
	}

	/**
	 * 当前日期转成字符串格式 格式为： 2016-01-18
	 * 
	 * @return
	 */
	public static String nowDateToString() {
		Date now = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(now);
	}

	public static String dateToString(Date date, String formatStr) {
		return new SimpleDateFormat(formatStr).format(date);
	}

	public static Date stringToDate(String dateStr, String formatStr) {
		DateFormat df = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * "yyyy-MM-dd HH:mm:ss" to Calendar
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Calendar parseStringDateToCalendar(String dateStr) throws Exception {
		if (dateStr == null || dateStr.trim().length() == 0)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = df.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(result);
		return cal;
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到某个事件之后的时间
	 * 
	 * @param d
	 *            某个时间
	 * @param type
	 *            后推的类型 1-年；2-月；5-日
	 * @param num
	 *            后推的数量 例如 type=1；num=1;表示时间d之后1年的时间
	 * @return
	 */
	public static Date getDateAfter(Date d, int type, int num) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(type, now.get(type) + num);
		return now.getTime();
	}

	/**
	 * 到截止时间还要多少天多少小时
	 * 
	 * @return
	 */
	public static String restTimeStr(String endTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String resultStr = "";
		try {
			Date now = new Date();
			Date end = df.parse(endTime);
			long l = end.getTime() - now.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			if (hour == 0 && hour == 0) {
				resultStr = day + "天";
			} else {
				resultStr = day + "天" + hour + "小时";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr;
	}

	/**
	 * @param format
	 * @return yyyy年MM月dd HH:mm MM-dd HH:mm 2012-12-25
	 * 
	 */
	public static String getDate(String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm 2012-12-29 23:47
	 */
	public static String getDateAndMinute() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm:ss 2012-12-29 23:47:36
	 */
	public static String getFullDate() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(calendar.getTime());
	}

	/**
	 * 距离今天多久
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String fromToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			if (ago < 300) {
				return "刚刚";
			} else {
				return ago / ONE_MINUTE + "分钟前";
			}
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟前";
		else if (ago <= ONE_YEAR) {
			return (new SimpleDateFormat("MM-dd HH:mm")).format(date);
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
		}
		// else if (ago <= ONE_DAY * 2)
		// return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
		// + calendar.get(Calendar.MINUTE) + "分";
		// else if (ago <= ONE_DAY * 3)
		// return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
		// + calendar.get(Calendar.MINUTE) + "分";
		// else if (ago <= ONE_MONTH) {
		// long day = ago / ONE_DAY;
		// return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
		// + calendar.get(Calendar.MINUTE) + "分";
		// } else if (ago <= ONE_YEAR) {
		// long month = ago / ONE_MONTH;
		// long day = ago % ONE_MONTH / ONE_DAY;
		// return month + "个月" + day + "天前"
		// + calendar.get(Calendar.HOUR_OF_DAY) + "点"
		// + calendar.get(Calendar.MINUTE) + "分";
		// } else {
		// long year = ago / ONE_YEAR;
		// int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so
		// month+1
		// return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
		// + "日";
		// }

	}

	/**
	 * 距离截止日期还有多长时间
	 * 
	 * @param date
	 * @return
	 */
	public static String fromDeadline(Date date) {
		long deadline = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long remain = deadline - now;
		if (remain <= ONE_HOUR)
			return "只剩下" + remain / ONE_MINUTE + "分钟";
		else if (remain <= ONE_DAY)
			return "只剩下" + remain / ONE_HOUR + "小时" + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
		else {
			long day = remain / ONE_DAY;
			long hour = remain % ONE_DAY / ONE_HOUR;
			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
		}

	}

	/**
	 * 距离今天的绝对时间
	 * 
	 * @param date
	 * @return
	 */
	public static String toToday(Date date) {
		long time = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
		else if (ago <= ONE_DAY * 2)
			return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY) % ONE_HOUR / ONE_MINUTE + "分";
		else if (ago <= ONE_DAY * 3) {
			long hour = ago - ONE_DAY * 2;
			return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE + "分";
		} else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			long hour = ago % ONE_DAY / ONE_HOUR;
			long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return day + "天前" + hour + "点" + minute + "分";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			long day = ago % ONE_MONTH / ONE_DAY;
			long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
			long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return month + "个月" + day + "天" + hour + "点" + minute + "分前";
		} else {
			long year = ago / ONE_YEAR;
			long month = ago % ONE_YEAR / ONE_MONTH;
			long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
			return year + "年前" + month + "月" + day + "天";
		}

	}

	public static void main(String args[]) {
		// String restTimeStr = restTimeStr("2018-12-23 14:00:00");
		// System.out.println(restTimeStr);
		// Date date= stringToDate("2018-12-23 14:00:00","yyyy-MM-dd");
		// System.out.println(date.toString());
		Date dateAfter = getDateAfter(new Date(), 2, 1);
		System.out.println(dateToString(dateAfter, "yyyy-MM-dd HH:mm:ss"));

	}

}
