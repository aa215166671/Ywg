package com.kyun.android.baisibudejie.pro.publish.view.Utilss;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static boolean SaveText(Context context,String text){

        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("text",text);
        editor.commit();
        return true;
    }
    public static Map <String, String> getText(Context context){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
            String text=sp.getString("text",null);
        Map<String,String> TEXT=new HashMap <String, String>();
        TEXT.put("text",text);
        return TEXT;
    }
}
