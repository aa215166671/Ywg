package com.kyun.android.baisibudejie.http;

//执行网络请求命令接口
public interface IHttpCommand<T> {
    public String execute(String url,IRequestParam<T> requstParam);
}
