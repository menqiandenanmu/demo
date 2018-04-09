package com.mall.butler.exception;
/**
 * 用户未登录
 * @author caedmon
 *
 */
public class NoLoginException extends ManageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452247674075019578L;

	public NoLoginException(String message){
		super(message);
	}
}
