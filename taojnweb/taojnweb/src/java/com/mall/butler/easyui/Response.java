package com.mall.butler.easyui;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Response {

	private Map<String, Object> datas = new HashMap<String, Object>();
	private Map<String, Object> attrs = new HashMap<String, Object>();

	public boolean isSuccess() {
		return (Boolean) this.datas.get("success");
	}

	public String getMessage() {
		return (String) this.datas.get("message");
	}

	public Response add(String key, Object value) {
		this.datas.put(key, value);
		return this;
	}

	public Response add(Map<String, Object> attrs) {
		this.datas.putAll(attrs);
		return this;
	}

	public Response addAttr(String key, Object value) {
		this.attrs.put(key, value);
		return this;
	}

	public Response addAttrs(Map<String, Object> attrs) {
		this.attrs.putAll(attrs);
		return this;
	}

	public Map<String, Object> getAttrs() {
		return Collections.unmodifiableMap(attrs);
	}

	/**
	 * 创建Response对象。
	 * 
	 * @return
	 */
	public static Response success() {
		return new InnerResponse(true);
	}

	/**
	 * 创建Response对象。
	 * 
	 * @param message
	 * @return
	 */
	public static Response success(String message) {
		return new InnerResponse(true, message);
	}

	/**
	 * 创建Response对象。
	 * 
	 * @return
	 */
	public static Response error() {
		return new InnerResponse(false);
	}

	/**
	 * 创建Response对象。
	 * 
	 * @param message
	 * @return
	 */
	public static Response error(String message) {
		return new InnerResponse(false, message);
	}

	private static class InnerResponse extends Response implements Map<String, Object> {

		private InnerResponse(boolean success) {
			super.datas.put("success", success);
		}

		private InnerResponse(boolean success, String message) {
			super.datas.put("success", success);
			super.datas.put("message", message);
		}

		public int size() {
			return super.datas.size();
		}

		public boolean isEmpty() {
			return super.datas.isEmpty();
		}

		public boolean containsKey(Object key) {
			return super.datas.containsKey(key);
		}

		public boolean containsValue(Object value) {
			return super.datas.containsValue(value);
		}

		public Object get(Object key) {
			return super.datas.get(key);
		}

		public Object put(String key, Object value) {
			return super.datas.put(key, value);
		}

		public Object remove(Object key) {
			return super.datas.remove(key);
		}

		public void putAll(Map<? extends String, ? extends Object> m) {
			super.datas.putAll(m);
		}

		public void clear() {
			super.datas.clear();
		}

		public Set<String> keySet() {
			return super.datas.keySet();
		}

		public Collection<Object> values() {
			return super.datas.values();
		}

		public Set<java.util.Map.Entry<String, Object>> entrySet() {
			return super.datas.entrySet();
		}
	}
}
