package com.example.apptruyentranh2.object;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapTruyen {
    private String id;
    private String tenChap;
    private String ngayDang;

    public ChapTruyen() {

    }

    public ChapTruyen(String tenChap, String ngayDang) {
        this.tenChap = tenChap;
        this.ngayDang = ngayDang;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChapTruyen(JSONObject o) {
        try {
            id = o.getString("id");
            tenChap = o.getString("tenchap");
            ngayDang = o.getString("ngaynhap");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
