package Departments;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.KeyStore;
import java.util.*;

public abstract class JsonReader {
    public static JsonObject getJson(String path) throws FileNotFoundException {
        Gson g = new Gson();
//        TypeToken<Object> map = (TypeToken<Object>) g.fromJson(new FileReader(path), TypeToken.class).getType();
//        System.out.println(map);
        Map<String, Object> retMap = new Gson().fromJson(
                new FileReader(path), new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        Collection col =retMap.values();
        System.out.println(col.toArray().getClass());
        return null;
    }

    public String jsonString = "";
    public Map<String, Object> retMap = new Gson().fromJson(
             jsonString, new TypeToken<HashMap<String, Object>>() {
            }.getType()
    );

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
