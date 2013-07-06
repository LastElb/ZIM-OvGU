package com.ovgu.jsondb;

import java.lang.reflect.Type;

import com.google.gson.*;
import com.ovgu.util.DatabaseEntry;

/**
 * This class hepls to deserialize the json object to an {@link DatabaseEntry}
 * @author Igor Lueckel
 *
 */
public class DBEntryDeserializer implements JsonDeserializer<DatabaseEntry> {

	@Override
	public DatabaseEntry deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		DatabaseEntry entry = new DatabaseEntry();
		JsonObject jsonObject = json.getAsJsonObject();
		
		entry.setUserID(jsonObject.get("userid").getAsString());
		entry.setContacts(jsonObject.get("contactcount").getAsString());
		entry.setContactTime(jsonObject.get("contacttime").getAsString());
		entry.setAnswerTime(jsonObject.get("answertime").getAsString());
		entry.setDate(jsonObject.get("date").getAsString());
		entry.setTime(jsonObject.get("time").getAsString());
		
		return entry;
	}

}
