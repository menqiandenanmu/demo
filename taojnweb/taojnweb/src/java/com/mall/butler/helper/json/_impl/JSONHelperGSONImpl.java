package com.mall.butler.helper.json._impl;

import com.mall.butler.helper.json.JSONHelper;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.mall.util.common.GenericUtil;
import com.mall.util.common.enums.AbstractEnumSupport;

@SuppressWarnings("unchecked")
public class JSONHelperGSONImpl implements JSONHelper {

	private static GsonBuilder gbuild;

	private static class EnumSupportTypeAdapter implements
			JsonSerializer<Enum>, JsonDeserializer<Enum> {
		private EnumSupportTypeAdapter() {
		}

		@Override
		public JsonElement serialize(Enum enumObj, Type enumType,
				JsonSerializationContext context) {
			if (enumObj instanceof AbstractEnumSupport)
				return new JsonPrimitive(((AbstractEnumSupport) enumObj)
						.getValue());
			else
				return new JsonPrimitive(enumObj.name());
		}

		@Override
		public Enum deserialize(JsonElement json, Type enumType,
				JsonDeserializationContext context) throws JsonParseException {
			if (!(json instanceof JsonPrimitive))
				throw new JsonParseException(
						"Supportted enum can only be deserialized from a integer.");
			if (AbstractEnumSupport.class.isAssignableFrom((Class) enumType))
				return (Enum) GenericUtil.valueOf(((Class<?>) enumType)
						.asSubclass(AbstractEnumSupport.class),
						((JsonPrimitive) json).getAsInt());
			else
				return Enum.valueOf((Class) enumType, json.getAsString());
		}
	}

	private static class DateTypeAdapter implements JsonSerializer<Date>,
			JsonDeserializer<Date> {
		private DateTypeAdapter() {
		}

		private static final DateFormat dformat = DateFormat.getDateInstance();
		private static final DateFormat dtformat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");

		@Override
		public JsonElement serialize(Date dateObj, Type dateType,
				JsonSerializationContext context) {
			return new JsonPrimitive(dformat.format(dateObj));
		}

		@Override
		public Date deserialize(JsonElement json, Type dateType,
				JsonDeserializationContext context) throws JsonParseException {
			if (!(json instanceof JsonPrimitive))
				throw new JsonParseException(
						"Supportted date can only be deserialized from a string.");
			Date v = null;
			try {
				v = dtformat.parse(json.getAsString());
			} catch (ParseException e) {
				try {
					v = dformat.parse(json.getAsString());
				} catch (ParseException e1) {
					throw new JsonParseException(
							"Can not parse the json value \""
									+ json.getAsString() + "\".");
				}
			}
			return v;
		}
	}

	private static class TimestampTypeAdapter implements
			JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
		private TimestampTypeAdapter() {
		}

		private static final DateFormat dformat = DateFormat.getDateInstance();
		private static final DateFormat dtformat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");

		@Override
		public JsonElement serialize(Timestamp dateObj, Type dateType,
				JsonSerializationContext context) {
			return new JsonPrimitive(dformat.format(dateObj));
		}

		@Override
		public Timestamp deserialize(JsonElement json, Type dateType,
				JsonDeserializationContext context) throws JsonParseException {
			if (!(json instanceof JsonPrimitive))
				throw new JsonParseException(
						"Supportted date can only be deserialized from a string.");
			Timestamp v = null;
			try {
				v = new Timestamp(dtformat.parse(json.getAsString()).getTime());
			} catch (ParseException e) {
				try {
					v = new Timestamp(dformat.parse(json.getAsString())
							.getTime());
				} catch (ParseException e1) {
					throw new JsonParseException(
							"Can not parse the json value \""
									+ json.getAsString() + "\".");
				}
			}
			return v;
		}
	}

	@Override
	public <T> T deserialize(String json, Class<T> clazz) {
		return gbuild.create().fromJson(json, clazz);
	}

	@Override
	public String serialize(Object object) {
		return gbuild.create().toJson(object);
	}

	@Override
	public <T> String serialize(Collection<T> object, Class<Collection<T>> clazz) {
		Type t = new TypeToken<Collection<T>>() {
		}.getType();
		return gbuild.create().toJson(object, t);
	}

	@Override
	public <T> Collection<T> deserialize(String json, Class<Collection<T>> clazz) {
		Type t = new TypeToken<Collection<T>>() {
		}.getType();
		return gbuild.create().fromJson(json, t);
	}

	public void init() {
		gbuild = new GsonBuilder();
		gbuild.registerTypeAdapter(Enum.class, new EnumSupportTypeAdapter());
		gbuild.registerTypeAdapter(Date.class, new DateTypeAdapter());
		gbuild.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter());
	}
}
