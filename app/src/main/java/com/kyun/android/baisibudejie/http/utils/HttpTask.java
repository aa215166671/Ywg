package com.kyun.android.baisibudejie.http.utils;

import android.os.AsyncTask;

import com.kyun.android.baisibudejie.http.IHttpCommand;
import com.kyun.android.baisibudejie.http.IRequestParam;

//异步任务执行网络请求
public class HttpTask extends AsyncTask<String,Void,String> {

    private String url;
    private IRequestParam requestParam;
    private HttpUtils.OnHttpResultListener onHttpResultListener;
    private IHttpCommand httpCommand;

    public HttpTask(String url, IRequestParam requestParam, HttpUtils.OnHttpResultListener onHttpResultListener, IHttpCommand httpCommand) {
        this.url = url;
        this.requestParam = requestParam;
        this.onHttpResultListener = onHttpResultListener;
        this.httpCommand = httpCommand;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            return httpCommand.execute(url,requestParam);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        if(this.onHttpResultListener!=null){
            this.onHttpResultListener.onResult(result);
        }
    }
}
