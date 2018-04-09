package com.mall.util.common.lang;

import com.mall.util.common.lang.exception.ChainedRuntimeException;

/**
 * 当<code>ObjectUtil.clone</code>方法被调用时，如果被复制的对象不支持该操作，则抛出该异常。
 * 
 * <p>
 * 注意，和<code>java.lang.CloneNotSupportedException</code>不同，该异常从
 * <code>RuntimeException</code>派生。
 * </p>
 * 
 * @author caedmon
 * @version $Id: ArrayUtil.java 2010-03-15
 */
public class CloneNotSupportedException extends ChainedRuntimeException {
	private static final long serialVersionUID = 3257281439807584562L;

	/**
	 * 构造一个空的异常.
	 */
	public CloneNotSupportedException() {
		super();
	}

	/**
	 * 构造一个异常, 指明异常的详细信息.
	 * 
	 * @param message
	 *            详细信息
	 */
	public CloneNotSupportedException(String message) {
		super(message);
	}

	/**
	 * 构造一个异常, 指明引起这个异常的起因.
	 * 
	 * @param cause
	 *            异常的起因
	 */
	public CloneNotSupportedException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造一个异常, 指明引起这个异常的起因.
	 * 
	 * @param message
	 *            详细信息
	 * @param cause
	 *            异常的起因
	 */
	public CloneNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}
}
