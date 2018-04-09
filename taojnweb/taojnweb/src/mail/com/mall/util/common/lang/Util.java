package com.mall.util.common.lang;

import java.util.Collections;
import java.util.Map;

import com.mall.util.common.lang.enumeration.EnumUtil;
import com.mall.util.common.lang.i18n.LocaleUtil;
import com.mall.util.common.lang.io.StreamUtil;

/**
 * 集成常用的工具类
 */

@SuppressWarnings("unchecked")
public class Util {

	public static final String DEFAULT_DATE_PATTERN_All = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_DATE_PATTERN_YMDHMS = "yyyyMMddHHmmss";
	public static final String DEFAULT_DATE_PATTERN = "yy-MM-dd HH:mm";
	public static final String DEFAULT_DATE_PATTERN_YEAR_MONTH_DATE = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_PATTERN_YEAR_MONTH_DATE_S = "yy-MM-dd";
	public static final String DEFAULT_DATE_PATTERN_HOUR_MINUTE_SECOND = "HH:mm:ss";
	public static final String DEFAULT_DATE_PATTERN_HOUR_MINUTE = "HH:mm";

	private static final ArrayUtil ARRAY_UTIL = new ArrayUtil();
	private static final ClassLoaderUtil CLASS_LOADER_UTIL = new ClassLoaderUtil();
	private static final ClassUtil CLASS_UTIL = new ClassUtil();
	private static final EnumUtil ENUM_UTIL = new EnumUtil();
	private static final ExceptionUtil EXCEPTION_UTIL = new ExceptionUtil();
	private static final FileUtil FILE_UTIL = new FileUtil();
	private static final LocaleUtil LOCALE_UTIL = new LocaleUtil();
	private static final MathUtil MATH_UTIL = new MathUtil();
	private static final MessageUtil MESSAGE_UTIL = new MessageUtil();
	private static final ObjectUtil OBJECT_UTIL = new ObjectUtil();
	private static final StreamUtil STREAM_UTIL = new StreamUtil();
	private static final StringEscapeUtil STRING_ESCAPE_UTIL = new StringEscapeUtil();
	private static final StringUtil STRING_UTIL = new StringUtil();
	private static final SystemUtil SYSTEM_UTIL = new SystemUtil();
	private static final NumberUtil NUMBER_UTIL = new NumberUtil();
	private static final DateUtil DATE_UTIL = new DateUtil();
	private static final EncrypterUtil ENCRYPTER_UTIL = new EncrypterUtil();

	private static final Map ALL_UTILS = Collections.unmodifiableMap(ArrayUtil
			.toMap(new Object[][] { { "arrayUtil", ARRAY_UTIL },
					{ "classLoaderUtil", CLASS_LOADER_UTIL },
					{ "classUtil", CLASS_UTIL }, { "enumUtil", ENUM_UTIL },
					{ "exceptionUtil", EXCEPTION_UTIL },
					{ "fileUtil", FILE_UTIL }, { "localeUtil", LOCALE_UTIL },
					{ "mathUtil", MATH_UTIL }, { "messageUtil", MESSAGE_UTIL },
					{ "objectUtil", OBJECT_UTIL },
					{ "streamUtil", STREAM_UTIL },
					{ "stringEscapeUtil", STRING_ESCAPE_UTIL },
					{ "stringUtil", STRING_UTIL },
					{ "systemUtil", SYSTEM_UTIL },
					{ "numberUtil", NUMBER_UTIL }, { "dateUtil", DATE_UTIL },
					{ "encrypterUtil", ENCRYPTER_UTIL } }));

	/**
	 * 取得<code>ArrayUtil</code>。
	 * 
	 * @return <code>ArrayUtil</code>实例
	 */
	public static ArrayUtil getArrayUtil() {
		return ARRAY_UTIL;
	}

	/**
	 * 取得<code>ClassLoaderUtil</code>。
	 * 
	 * @return <code>ClassLoaderUtil</code>实例
	 */
	public static ClassLoaderUtil getClassLoaderUtil() {
		return CLASS_LOADER_UTIL;
	}

	/**
	 * 取得<code>ClassUtil</code>。
	 * 
	 * @return <code>ClassUtil</code>实例
	 */
	public static ClassUtil getClassUtil() {
		return CLASS_UTIL;
	}

	/**
	 * 取得<code>EnumUtil</code>。
	 * 
	 * @return <code>EnumUtil</code>实例
	 */
	public static EnumUtil getEnumUtil() {
		return ENUM_UTIL;
	}

	/**
	 * 取得<code>ExceptionUtil</code>。
	 * 
	 * @return <code>ExceptionUtil</code>实例
	 */
	public static ExceptionUtil getExceptionUtil() {
		return EXCEPTION_UTIL;
	}

	/**
	 * 取得<code>FileUtil</code>。
	 * 
	 * @return <code>FileUtil</code>实例
	 */
	public static FileUtil getFileUtil() {
		return FILE_UTIL;
	}

	/**
	 * 取得<code>LocaleUtil</code>。
	 * 
	 * @return <code>LocaleUtil</code>实例
	 */
	public static LocaleUtil getLocaleUtil() {
		return LOCALE_UTIL;
	}

	/**
	 * 取得<code>MathUtil</code>。
	 * 
	 * @return <code>MathUtil</code>实例
	 */
	public static MathUtil getMathUtil() {
		return MATH_UTIL;
	}

	/**
	 * 取得<code>MessageUtil</code>。
	 * 
	 * @return <code>MessageUtil</code>实例
	 */
	public static MessageUtil getMessageUtil() {
		return MESSAGE_UTIL;
	}

	/**
	 * 取得<code>ObjectUtil</code>。
	 * 
	 * @return <code>ObjectUtil</code>实例
	 */
	public static ObjectUtil getObjectUtil() {
		return OBJECT_UTIL;
	}

	/**
	 * 取得<code>StreamUtil</code>。
	 * 
	 * @return <code>StreamUtil</code>实例
	 */
	public static StreamUtil getStreamUtil() {
		return STREAM_UTIL;
	}

	/**
	 * 取得<code>StringEscapeUtil</code>。
	 * 
	 * @return <code>StringEscapeUtil</code>实例
	 */
	public static StringEscapeUtil getStringEscapeUtil() {
		return STRING_ESCAPE_UTIL;
	}

	/**
	 * 取得<code>StringUtil</code>。
	 * 
	 * @return <code>StringUtil</code>实例
	 */
	public static StringUtil getStringUtil() {
		return STRING_UTIL;
	}

	/**
	 * 取得<code>SystemUtil</code>。
	 * 
	 * @return <code>SystemUtil</code>实例
	 */
	public static SystemUtil getSystemUtil() {
		return SYSTEM_UTIL;
	}

	/**
	 * 取得<code>NumberUtil</code>
	 * 
	 * @return<code>NumberUtil</code>实例
	 */
	public static NumberUtil getNumberUtil() {
		return NUMBER_UTIL;
	}

	/**
	 * 取得<code>DateUtil</code>
	 * 
	 * @return<code>DateUtil</code>实例
	 */
	public static DateUtil getDateUtil() {
		return DATE_UTIL;
	}

	/**
	 * 取得<code>MD5Util</code>
	 * 
	 * @return<code>MD5Util</code>实例
	 */
	public static EncrypterUtil getEncrypterUtil() {
		return ENCRYPTER_UTIL;
	}

	/**
	 * 取得包含所有utils的map
	 * 
	 * @return utils map
	 */
	public static Map<String, Object> getUtils() {
		return ALL_UTILS;
	}

}
