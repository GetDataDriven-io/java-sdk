package io.getdatadriven;


import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Event {

	private final String name;

	private final Map<String, Object> data = new HashMap<>();

	public Event(final String name) {
		Preconditions.checkNotNull(name, "Event name is required.");

		this.name = name;
	}

	public Event withTimestamp(final long timestamp) {
		data.put("timestamp", toIsoString(timestamp));
		return this;
	}

	public Event with(final String fieldName, final String fieldValue) {
		Preconditions.checkNotNull(fieldValue, "Field name is required.");

		data.put(fieldName, fieldValue);
		return this;
	}

	public Event with(final String fieldName, final Boolean fieldValue) {
		Preconditions.checkNotNull(fieldValue, "Field name is required.");

		data.put(fieldName, fieldValue);
		return this;
	}

	public Event with(final String fieldName, final Double fieldValue) {
		Preconditions.checkNotNull(fieldValue, "Field name is required.");

		data.put(fieldName, fieldValue);
		return this;
	}

	public Event with(final String fieldName, final Integer fieldValue) {
		Preconditions.checkNotNull(fieldValue, "Field name is required.");

		data.put(fieldName, fieldValue);
		return this;
	}

	String getName() {
		return name;
	}

	String jsonData() {
		return JSONObject.fromObject(data).toString();
	}

	private String toIsoString(final long timestamp) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		return df.format(new Date(timestamp));
	}
}
