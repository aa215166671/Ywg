package com.kyun.android.baisibudejie.pro.essense.view.essence_all.SD_file;

public class Ware {
    private long id;
    private String warename;

    public Ware(long id, String warename) {
        this.id = id;
        this.warename = warename;
    }

    public Ware(String warename) {
        this.warename = warename;
    }

    public Ware()
    {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarename() {
        return warename;
    }

    public void setWarename(String warename) {
        this.warename = warename;
    }

    public String toString()
    {
        return  "[序号："+id+",商品名称："+warename+"]";
    }

}

