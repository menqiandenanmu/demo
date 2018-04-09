package com.mall.butler.exception;
/**
 * 管理平台异常
 * @author caedmon
 *
 */
public class ManageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8238535965057151913L;
	public ManageException(String message){
		super(message);
	}
}
