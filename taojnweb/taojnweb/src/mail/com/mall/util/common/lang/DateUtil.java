package com.mall.util.common.lang;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 日期处理类
 * 
 * @author caedmon Version 2010-6-11
 */
public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 获得timestamp类型的当前系统日期
	 * 
	 * @return
	 */
	public static Timestamp getDateTime() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 日期转换成String类型
	 * 
	 * @param value
	 *            日期值
	 * @param format
	 *            转换格式
	 * @return
	 */
	public static String format(Date value, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(value);
	}

	/**
	 * 根据参数获得java.sql.Date类型 dateTime为需要转换的日期参数，pattern为转换格式。 方法调用:
	 * getDateTime("",Util.DEFAULT_DATE_PATTERN_All)，则按默认时间格式输出，格式如下
	 * <code>yyyy-MM-dd HH:mm:ss</code>
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return java.sql.Date
	 */
	public static java.sql.Date getDateTime(String dateTime, String pattern) {
		SimpleDateFormat dateFormat = getSimpleDateFormat(pattern);
		java.sql.Date date2 = null;
		try {
			Date date = dateFormat.parse(dateTime);
			date2 = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
		}
		return date2;
	}

	/**
	 * 根据参数获得java.sql.Date类型 根据年、月、日参数和pattern指定的转换格式，返回对应的日期字符串。 方法调用:
	 * getDateTime(Util.DEFAULT_DATE_PATTERN_All,2010,6,10)，则按默认时间格式输出内容如下
	 * <code>2010-06-10 00:00:00</code>
	 * 
	 * @param pattern
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @return java.sql.Date
	 */
	public static String getDateTime(String pattern, int year, int month,
			int date) {
		SimpleDateFormat df = getSimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date);
		return df.format(c.getTime());
	}

	/**
	 * 根据指定日期转换格式获得对应日期的时间字符串
	 * 
	 * @param pattern
	 * @param dateTime
	 * @return String
	 */
	public static String getDateTime(String pattern, Object dateTime) {
		return getSimpleDateFormat(pattern).format(dateTime);
	}

	/**
	 * 获得 java.text.SimpleDateFormat
	 * 
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 获得指定日期格式的指定日期时间范围内的所有日期。
	 * 
	 * @param pattern
	 *            指定日期格式，若参数默认为Util.DEFAULT_DATE_PATTERN_YEAR_MONTH_DATE
	 * @param startdate
	 *            开始时间
	 * @param enddate
	 *            结束时间
	 * @return String[]
	 */
	public static String[] getHotelDate(String pattern, String startdate,
			String enddate) {
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		try {
			Date d1 = f.parse(startdate);
			Date d2 = f.parse(enddate);
			long days = (d2.getTime() - d1.getTime()) / 3600000 / 24;
			Calendar startcal = Calendar.getInstance();
			Calendar endcal = Calendar.getInstance();
			startcal.setTime(d1);
			endcal.setTime(d2);
			String hotelDate[] = new String[(int) days + 1];
			hotelDate[0] = f.format(d1);
			int i = 1;
			while (endcal.after(startcal)) {
				if (i <= days)
					startcal.add(Calendar.DATE, 1);
				hotelDate[i] = f.format(startcal.getTime());
				i++;
			}
			return hotelDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得当前日期为每周的第几天
	 * 
	 * @param pattern
	 * @param dateTime
	 * @return
	 */
	public static int getDayOfWeek(String pattern, String dateTime) {
		Date date = null;
		try {
			date = getSimpleDateFormat(pattern).parse(dateTime);
		} catch (ParseException e) {
			logger.error("解析日期格式错误，错误信息为：" + e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);
		if (weekOfDay == 0) {
			weekOfDay = 7;
		}
		return weekOfDay;
	}

	/**
	 * 获得当前日期是每周的周几
	 * 
	 * @return String
	 */
	public static String getCurrentDayofWeek() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat dateFormat = getSimpleDateFormat("yyyy-MM-dd");
		String dateTime = dateFormat.format(new Date());
		int sIndex = dateTime.indexOf("-");
		int eIndex = dateTime.lastIndexOf("-");
		String year = dateTime.substring(0, sIndex);
		String month = dateTime.substring(sIndex + 1, eIndex);
		String day = dateTime.substring(eIndex + 1, dateTime.length());
		sb.append("日期：");
		sb.append(year + "年");
		sb.append(month + "月");
		sb.append(day + "日");
		int temp = getDayOfWeek("yyyy-MM-dd", dateTime);
		switch (temp - 1) {
		case 0:
			sb.append(" 周日");
			break;
		case 1:
			sb.append(" 周一");
			break;
		case 2:
			sb.append(" 周二");
			break;
		case 3:
			sb.append(" 周三");
			break;
		case 4:
			sb.append(" 周四");
			break;
		case 5:
			sb.append(" 周五");
			break;
		case 6:
			sb.append(" 周六");
			break;
		default:
			break;
		}
		return sb.toString();
	}

	/**
	 * 获得当前日期周的周一
	 * 
	 * @return
	 */
	public static String getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date modayDate = null;
		try {
			modayDate = dateFormat.parse(dateFormat.format(monday));
		} catch (ParseException e) {
			logger.error("解析日期格式错误，错误信息为：" + e.getMessage());
		}
		return dateFormat.format(modayDate);
	}

	/**
	 * 获得当前日期周的周日
	 * 
	 * @return
	 */
	public static String getCurrentSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date modayDate = null;
		try {
			modayDate = dateFormat.parse(dateFormat.format(monday));
		} catch (ParseException e) {
			logger.error("解析日期格式错误，错误信息为：" + e.getMessage());
		}
		return dateFormat.format(modayDate);
	}

	/**
	 * 获得当前日期与本周一相差的天数
	 * 
	 * @return
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 系统日期大于等于比较日期
	 * 
	 * @param time
	 *            比较时间
	 * @return
	 */
	public boolean greaterThanOrEqualDateTime(long dateTime) {
		long now = new Date().getTime();
		if (now >= dateTime) {
			return true;
		}
		return false;
	}

	/**
	 * 系统日期大于比较日期
	 * 
	 * @param time
	 *            比较时间
	 * @return
	 */
	public boolean greaterThanDateTime(long dateTime) {
		long now = new Date().getTime();
		if (now > dateTime) {
			return true;
		}
		return false;
	}

	/**
	 * 系统日期小于等于比较日期
	 * 
	 * @param time
	 *            比较时间
	 * @return
	 */
	public boolean lessThanOrEqualDateTime(long dateTime) {
		long now = new Date().getTime();
		if (now <= dateTime) {
			return true;
		}
		return false;
	}

	/**
	 * 系统日期小于比较日期
	 * 
	 * @param time
	 *            比较时间
	 * @return
	 */
	public boolean lessThanDateTime(long dateTime) {
		long now = new Date().getTime();
		if (now < dateTime) {
			return true;
		}
		return false;
	}

	/**
	 * 计算给定时间与当前时间的间距时分秒
	 * 
	 * @param d
	 * @return int[]
	 */
	public static int[] getHMSBetweenTwoTime(Date d) {
		int[] rtn = new int[] { 0, 0, 0, 0 };
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		long ss = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
		if (ss != 0) {
			rtn[0] = (int) ss / 1000;
		}
		if (ss % (60 * 1000) != 0) {
			rtn[1] = (int) (ss / (60 * 1000));
		}
		if (ss % (60 * 60 * 1000) != 0) {
			rtn[2] = (int) (ss / (60 * 60 * 1000));
		}
		if (ss % (60 * 60 * 24 * 1000) != 0) {
			rtn[3] = (int) (ss / (60 * 60 * 24 * 1000));
		}
		return rtn;
	}

	public static enum DateFormat {
		DEFAULT, SHORT, SINGLE, CN_SHORT, CN_LONG, CURRENT_TIME, SLASH
	}

	private static SimpleDateFormat[] FORMATS = new SimpleDateFormat[] {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyyMMdd"),
			new SimpleDateFormat("yyyy年MM月dd日"),
			new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒"),
			new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss"),
			new SimpleDateFormat("yyyy/MM/dd"), };

	public static boolean isDate(String value) {
		for (int i = 0; i < DateFormat.values().length; i++) {
			try {
				FORMATS[DateFormat.values()[i].ordinal()].parse(value);
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}

	/** 判断是否有冲突周start1~周end1与周start2~周end2 */
	public static boolean isClash(int start1, int end1, int start2, int end2) {
		// 得出所有周x日期放入list判断是否存在
		List<Integer> days1 = new ArrayList<Integer>();
		List<Integer> days2 = new ArrayList<Integer>();
		while (start1 % 7 != end1) {
			days1.add(start1++ % 7);
		}
		days1.add(end1);
		while (start2 % 7 != end2) {
			days2.add(start2++ % 7);
		}
		days2.add(end2);
		for (Iterator<Integer> iter = days1.iterator(); iter.hasNext();) {
			if (days2.contains(iter.next()))
				return false;
		}
		return true;
	}

	/**
	 * @Description: 组合时间
	 * @date 2009-11-4 下午01:43:36
	 * @param start
	 * @param end
	 * @param time
	 * @return List<Date>
	 */
	public static List<Date> getComposeDates(Date start, Date end, Date time) {
		List<Date> dates = new ArrayList<Date>();
		int count = DateUtil.getBetweenDay(start, end).intValue();
		if (count < 0)
			return dates;
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		for (int i = 0; i <= count; i++) {
			Date dd = addDays(start, i);
			Calendar day = Calendar.getInstance();
			day.setTime(dd);
			day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
			day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
			dates.add(day.getTime());
		}
		return dates;
	}

	public static Date toDate(String value) {
		for (int i = 0; i < DateFormat.values().length; i++) {
			try {
				Date result = FORMATS[DateFormat.values()[i].ordinal()]
						.parse(value);
				return result;
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static String format(Date date, DateFormat format) {
		return FORMATS[format.ordinal()].format(date);
	}

	public static Date parse(String value, String format) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.parse(value);
	}

	public static Date parse(String value, DateFormat f) throws ParseException {
		return FORMATS[f.ordinal()].parse(value);
	}

	public static Long getBetweenDay(Date date1, Date date2) {
		Long days = 0L;
		if (date1.getTime() > date2.getTime())
			days = date1.getTime() - date2.getTime();
		else
			days = date2.getTime() - date1.getTime();
		return days / 60 / 60 / 1000 / 24;
	}

	public static Date cutTimeToDate(Date date) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.set(Calendar.HOUR_OF_DAY, 0);
		d.set(Calendar.MINUTE, 0);
		d.set(Calendar.SECOND, 0);
		d.set(Calendar.MILLISECOND, 0);
		return d.getTime();
	}

	/**
	 * 
	 * @Function:根据给定的日期字符串,产生一个时间从0点开始计算的Timestamp
	 * @param date
	 *            日期(不要包含时间)字符串
	 * @return Timestamp
	 * 
	 */
	public static Timestamp getTimeDayStart(String date) {
		if (date == null || date.equalsIgnoreCase("")) {
			Calendar d = Calendar.getInstance();
			Timestamp stamp = new Timestamp(d.getTimeInMillis());
			// hh:mm:ss
			d.set(Calendar.HOUR_OF_DAY, 0);
			d.set(Calendar.MINUTE, 0);
			d.set(Calendar.SECOND, 1);
			return stamp;
		}
		String[] arrayDate = date.split("-");
		try {
			Calendar d = Calendar.getInstance();
			int currentYear = Integer.valueOf(arrayDate[0]).intValue();
			int currentMonth = Integer.valueOf(arrayDate[1]).intValue();
			int currentDay = Integer.valueOf(arrayDate[2]).intValue();
			d.set(Calendar.YEAR, currentYear);
			d.set(Calendar.MONTH, currentMonth - 1);
			d.set(Calendar.DAY_OF_MONTH, currentDay);
			d.set(Calendar.HOUR_OF_DAY, 0);
			d.set(Calendar.MINUTE, 0);
			d.set(Calendar.SECOND, 1);
			Timestamp stamp = new Timestamp(d.getTimeInMillis());
			return stamp;
		} catch (Exception e) {
			System.out.println("your impart a wrong date String");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Function:根据给定的日期字符串,产生一个当天时间从23点59分59秒开始计算的Timestamp
	 * @param date
	 *            日期(不要包含时间)字符串
	 * @return Timestamp
	 */
	public static Timestamp getTimeDayLast(String date) {
		if (date == null || date.equalsIgnoreCase("")) {
			Calendar d = Calendar.getInstance();
			// hh:mm:ss
			d.set(Calendar.HOUR_OF_DAY, 23);
			d.set(Calendar.MINUTE, 59);
			d.set(Calendar.SECOND, 59);
			Timestamp stamp = new Timestamp(d.getTimeInMillis());
			return stamp;
		}
		String[] arrayDate = date.split("-");
		try {
			Calendar d = Calendar.getInstance();

			int currentYear = Integer.valueOf(arrayDate[0]).intValue();
			int currentMonth = Integer.valueOf(arrayDate[1]).intValue();
			int currentDay = Integer.valueOf(arrayDate[2]).intValue();
			d.set(Calendar.YEAR, currentYear);
			d.set(Calendar.MONTH, currentMonth - 1);
			d.set(Calendar.DAY_OF_MONTH, currentDay);
			// hh:mm:ss
			d.set(Calendar.HOUR_OF_DAY, 23);
			d.set(Calendar.MINUTE, 59);
			d.set(Calendar.SECOND, 59);
			Timestamp stamp = new Timestamp(d.getTimeInMillis());
			return stamp;
		} catch (Exception e) {
			System.out.println("your impart a wrong date String");
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isToday(Date date) {
		if (date == null)
			return false;
		Calendar cal = Calendar.getInstance();
		int year1 = cal.get(Calendar.YEAR);
		int day1 = cal.get(Calendar.DAY_OF_YEAR);

		cal.setTime(date);
		int year2 = cal.get(Calendar.YEAR);
		int day2 = cal.get(Calendar.DAY_OF_YEAR);

		return year1 == year2 && day1 == day2;
	}

	public static boolean isWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday == Calendar.SUNDAY || weekday == Calendar.SATURDAY)
			return true;
		else
			return false;
	}

	// 得到几月之前的当天
	public static Date getbeforDate(Date date, int count) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -count);
		return cal.getTime();
	}

	// 天数操作 Ds(-3));//三天前的时间
	public static String Ds(int days) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		Date cc = calendar.getTime();
		return form.format(cc);
	}

	/**
	 * 在当前时间上加几天
	 * 
	 * @param days
	 * @return Date
	 */
	public static Date addDays(int days) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		return calendar.getTime();
	}

	/**
	 * 对传入的日期加上几天
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		return calendar.getTime();
	}

	/**
	 * 在当前时间上减几天
	 * 
	 * @param days
	 * @return Date
	 */
	public static Date cutDays(int days) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day - days);
		return calendar.getTime();
	}

	/**
	 * 对传入的日期减上几天
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date cutDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day - days);
		return calendar.getTime();
	}

	/**
	 * 得到有效年数
	 * 
	 * @param year
	 * @return
	 */
	public static Date addYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, day + year);
		return calendar.getTime();
	}

	/**
	 * @Description: 得到增加年份的当天
	 * @throws
	 */
	public static Date addYear(Date data, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		int day = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, day + year);
		return calendar.getTime();
	}

	// 月数操作 MonthAdd(-4));//四月前的时间
	public static String MonthAdd(int days) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, day + days);
		Date cc = calendar.getTime();
		return form.format(cc);
	}

	public static Date addDayHmS(Date date, int HH, int mm, int SS) {
		return new Date(date.getTime() + ((HH * 60 + mm) * 60 + SS) * 1000);
	}

	public static Date getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();

	}

	/**
	 * 得到传入日期当月的最后一天
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getMonthLastDay(Date date) {
		Date d = DateUtil.addDays(DateUtil.addMonth(DateUtil
				.getMonthFirstDay(date), 1), -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Date addMonth(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param date
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	// 得到当天最晚时间
	public static Date dayLastTime(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);

		return cal.getTime();
	}

	// 得到当天最早时间
	public static Date dayFirstTime(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MILLISECOND, 1);
		cal.add(Calendar.DATE, 0);
		return cal.getTime();
	}

	/**
	 * @Description: 得到单季度最早时间
	 * @date 2009-9-9 下午01:15:51
	 * @param session
	 * @return
	 * @return Date
	 */
	public static Date getFirstDayOfSeason(Date date) {
		int season = getQuarterly(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (season) {
		case 0:
			cal.set(Calendar.MONTH, 0);
			break;
		case 1:
			cal.set(Calendar.MONTH, 3);
			break;
		case 2:
			cal.set(Calendar.MONTH, 6);
			break;
		case 3:
			cal.set(Calendar.MONTH, 9);
			break;
		}
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 0);
		return cal.getTime();
	}

	public static Date getLastDayOfSeason(Date date) {
		int season = getQuarterly(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (season) {
		case 0:
			cal.set(Calendar.MONTH, 3);
			break;
		case 1:
			cal.set(Calendar.MONTH, 6);
			break;
		case 2:
			cal.set(Calendar.MONTH, 9);
			break;
		case 3:
			cal.add(Calendar.YEAR, 1);
			cal.set(Calendar.MONTH, 0);
			break;
		}
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MILLISECOND, -1);
		return cal.getTime();
	}

	public static int getQuarterly(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) / 3;
	}

	/**
	 * 计算两个时间之间的间距时分秒
	 * 
	 * @param d1
	 *            小的时间
	 * @param d2
	 *            大的时间
	 * @return int[]
	 */
	public static int[] getHMSBetweenTwoTime(Date d1, Date d2) {
		int[] rtn = new int[] { 0, 0, 0 };
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		long ss = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
		if (ss != 0) {
			rtn[0] = (int) ss / (60 * 60);
		}
		if (ss % (60 * 60) != 0) {
			rtn[1] = (int) (ss % (60 * 60)) / 60;
			rtn[2] = (int) ((ss % (60 * 60)) % 60);
		}
		return rtn;
	}

	public static Date trimToDay(Date date) {
		if (null == date)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 根据时间范围获得按天统计的日期集合yyyy-mm-dd
	 * 
	 * @author : zhuwei
	 * @date ：2010-5-26 上午10:40:08
	 * @param lower
	 * @param upper
	 * @return
	 */
	public static List<String> getDays(Date lower, Date upper) {
		List<String> result = new ArrayList<String>();
		while (lower.getTime() <= upper.getTime()) {
			result.add(DateUtil.format(lower, DateFormat.SHORT));
			lower = DateUtil.addDays(lower, 1);
		}
		return result;
	}

	/**
	 * 一天中的问候语
	 * 
	 * @return
	 */
	public static String getGreetings() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 6 && hour < 8) {
			return "早上好！";
		} else if (hour >= 8 && hour < 11) {
			return "上午好！";
		} else if (hour >= 11 && hour < 13) {
			return "中午好！";
		} else if (hour >= 13 && hour < 18) {
			System.out.println("");
			return "下午好！";
		} else {
			return "晚上好！";
		}
	}

	public static void main(String[] args) {
		Date date = getDateTime("2010-07-15 08:00:00", "yyyy-MM-dd HH:mm:ss");
		int time[] = DateUtil.getHMSBetweenTwoTime(date);
		System.out.println("---" + time[0] + "---" + time[1] + "---" + time[2]);
	}

	/**
	 * 判断日期是between
	 * 
	 * @author caedmon
	 * @date 2010-12-13 下午04:59:55
	 * @param date
	 * @param begDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBetween(Date date, Date begDate, Date endDate) {
		if (date.before(begDate) || date.after(endDate))
			return false;
		else
			return true;
	}

	public static List<String> getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}

		String startMonth = "", endMonth = "";

		int start_month = array[season - 1][0];
		if (start_month < 10) {
			startMonth = "0" + start_month;
		} else {
			startMonth = String.valueOf(start_month);
		}

		int end_month = array[season - 1][2];
		if (end_month < 10) {
			endMonth = "0" + endMonth;
		} else {
			endMonth = String.valueOf(end_month);
		}

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		String start_days = "01";
		String end_days = getLastDayOfMonth(years_value, end_month);
		String seasonStDate = years_value + "-" + startMonth + "-" + start_days;// +";"+years_value+"-"+end_month+"-"+end_days;
		String seasonEdDate = years_value + "-" + endMonth + "-" + end_days;// +";"+years_value+"-"+end_month+"-"+end_days;

		List<String> reDateList = new ArrayList<String>();

		reDateList.add(seasonStDate);
		reDateList.add(seasonEdDate);

		return reDateList;
	}

	private static String getLastDayOfMonth(int year, int month) {

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8

		|| month == 10 || month == 12) {

			return "31";

		}

		if (month == 4 || month == 6 || month == 9 || month == 11) {

			return "30";

		}

		if (month == 2) {

			if (isLeapYear(year)) {

				return "29";

			} else {

				return "28";

			}

		}

		return "";

	}

	public static boolean isLeapYear(int year) {

		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

	}

}