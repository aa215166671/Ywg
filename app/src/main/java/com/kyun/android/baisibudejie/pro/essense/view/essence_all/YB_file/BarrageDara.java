package com.kyun.android.baisibudejie.pro.essense.view.essence_all.YB_file;

/**
 * 作者：Loco on 2019/7/26.
 * 邮箱：8752439@qq.com
 * 版本：v1.0
 */


import com.kyun.android.baisibudejie.pro.essense.view.views.barragephoto.model.DataSource;

/**
 * 弹幕数据
 *
 * Created by wangjie on 2019/3/22.
 */

@SuppressWarnings("ALL")
public class BarrageDara implements DataSource {

    private String content;
    private int type;
    private int pos;

    public BarrageDara(String content, int type, int pos) {
        this.content = content;
        this.type = type;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }
}