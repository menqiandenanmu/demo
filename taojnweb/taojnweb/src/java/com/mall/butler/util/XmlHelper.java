package com.mall.butler.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.mall.butler.xml.BaseXml;

/**
 * 
 * @author caedmon
 * 
 */
public class XmlHelper {

	@SuppressWarnings("unchecked")
	public static <T extends BaseXml> T toObj(Class<T> clazz, String xml) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			InputStream buf = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			T result = (T) context.createUnmarshaller().unmarshal(buf);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T extends BaseXml> String toXml(T obj) {
		try {
			JAXBContext context;
			StringWriter write = new StringWriter();
			context = JAXBContext.newInstance(obj.getClass());
			context.createMarshaller().marshal(obj, write);
			return write.getBuffer().toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			return "";
		}
	}

}
