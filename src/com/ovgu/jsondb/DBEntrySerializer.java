package com.ovgu.jsondb;

import java.lang.reflect.Type;

import com.google.gson.*;
import com.ovgu.util.DatabaseEntry;

public class DBEntrySerializer implements JsonSerializer<DatabaseEntry> {

	@Override
	public JsonElement serialize(DatabaseEntry src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject result = new JsonObject();
        result.add("userid", new JsonPrimitive(src.getUserID()));
        result.add("date", new JsonPrimitive(src.getDate()));
        result.add("time", new JsonPrimitive(src.getTime()));
        result.add("answertime", new JsonPrimitive(src.getAnswerTime()));
        result.add("contactcount", new JsonPrimitive(src.getContacts()));
        result.add("contacttime", new JsonPrimitive(src.getAnswerTime()));
        return result;
	}

}
