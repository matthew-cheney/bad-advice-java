package main.BadAdvice.json_util;

import com.google.gson.Gson;

public class JsonConverter {

    public static <T> T jsonToModel(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

    public static String modelToJson(Object model_in) {
        return new Gson().toJson(model_in);
    }

}
