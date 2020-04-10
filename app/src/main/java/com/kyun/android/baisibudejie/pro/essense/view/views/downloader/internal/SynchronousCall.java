package com.kyun.android.baisibudejie.pro.essense.view.views.downloader.internal;

import com.kyun.android.baisibudejie.pro.essense.view.views.downloader.Response;
import com.kyun.android.baisibudejie.pro.essense.view.views.downloader.request.DownloadRequest;

public class SynchronousCall {

    public final DownloadRequest request;

    public SynchronousCall(DownloadRequest request) {
        this.request = request;
    }

    public Response execute() {
        DownloadTask downloadTask = DownloadTask.create(request);
        return downloadTask.run();
    }

}
