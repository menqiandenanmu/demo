package com.mall.butler.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ReflectionUtils;

import com.mall.butler.easyui.Response;
import com.mall.butler.exception.BusinessException;
import com.mall.butler.util.JsonMapper;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 异步请求异常处理拦截器。
 * 
 * @author liwei
 */
public class AsyncExceptionInterceptor implements Interceptor {

	private static final long serialVersionUID = -786211121537462672L;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (BusinessException be) {
			if (this.isAsyncRequest(invocation)) {
				this.renderErrorMessage(be.getMessage());
				return Action.NONE;
			} else {
				throw be;
			}
		} catch (Exception t) {
			if (this.isAsyncRequest(invocation)) {
				this.renderErrorMessage("系统中心系统异常！错误：" + t.getMessage());
				return Action.NONE;
			} else {
				throw t;
			}
		}
	}

	/**
	 * 获取该方法上的注解，
	 * 
	 * @param invocation
	 * @return
	 */
	protected boolean isAsyncRequest(ActionInvocation invocation) {
		Object action = invocation.getProxy().getAction();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(action.getClass(), methodName);
		Async async = method.getAnnotation(Async.class);
		return async != null;
	}

	/**
	 * 将错误消息写入Response流。
	 * 
	 * @param message
	 */
	protected void renderErrorMessage(String message) {
		PrintWriter out = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json;charset=UTF-8");
			out = response.getWriter();
			out.write(JsonMapper.toJson(Response.error(message)));
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException("Response writing failure.", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
