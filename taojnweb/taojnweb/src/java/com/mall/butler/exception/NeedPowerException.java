package com.mall.butler.exception;

/**
 * 无访问权限
 * @author caedmon
 *
 */
public class NeedPowerException extends ManageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452247674075019578L;

	public NeedPowerException(String message){
		super(message);
	}
}
