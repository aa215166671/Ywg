package com.kyun.android.baisibudejie.pro.essense.view.essence_all.JH_file;

import java.net.URL;
import java.util.List;

class Bean {
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List <DataBean> getData() {
        return data;
    }

    public void setData(List <DataBean> data) {
        this.data = data;
    }

    private int status;
    private String info;
    private List <DataBean> data;

    public static class DataBean {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private int id;
        private String num;
        private String url;
        private String text;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
