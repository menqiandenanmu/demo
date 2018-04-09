package com.mall.util.ibatis.exception;

public class XibatisTxException extends Exception {
    private static final long serialVersionUID = 2491938659756615245L;
    private String            code;

    public XibatisTxException(){
    	super();
    }
    public XibatisTxException(String message) {
        this(null, message);
    }

    public XibatisTxException(String code, String message) {
        this(code, message, null);
    }

    public XibatisTxException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
