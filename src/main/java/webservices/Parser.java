package webservices;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Parser {

    public static String toJson(final Object object) {
        return new Gson().newBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(object);
    }

    public static <T> T fromJson(final String json, final Class<T> returnType) {
        return new Gson().fromJson(json, returnType);
    }
}
