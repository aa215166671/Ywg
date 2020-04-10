package com.kyun.android.baisibudejie.pro.publish.view.Utilss;
import com.google.gson.Gson;
import com.kyun.android.baisibudejie.bean.User;

public class parseJSONWithGsonUtils {
    public static User parseJSONWithGson(String jsonData)    {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, User.class);
    }
}
