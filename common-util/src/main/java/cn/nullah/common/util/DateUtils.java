/**
 * DateUtils.java Copyright ©2016 http://www.nullah.cn All Rights Reserved
 */
package cn.nullah.common.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author zxy@nullah.cn
 * @note ...
 */
public class DateUtils {
	public static final String DATE_STYLE_NUM_14 = "yyyyMMddHHmmss";

	/**
	 * 获取凌晨时间
	 * 
	 * @param day
	 *            日加减参数,+1表示+1天,-1表示-1天
	 * @return 指定加减日的凌晨时间
	 */
	public static Date getWeeHours(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		return getWeeHours(cal.getTime());
	}

	/**
	 * 时间格式yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getDateNum14() {
		return DateFormatUtils.format(Calendar.getInstance(), DateUtils.DATE_STYLE_NUM_14);
	}

	/**
	 * 凌晨
	 * 
	 * @param date
	 * @return 指定日期的凌晨时间
	 */
	public static Date getWeeHours(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
		return cal.getTime();
	}
}
