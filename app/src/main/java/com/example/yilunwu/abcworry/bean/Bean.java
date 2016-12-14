package com.example.yilunwu.abcworry.bean;

/**
 * Created by yilunwu on 16/12/10.
 */
public class Bean {
    private String title;
    private String desc;
    private String time;
    private String phone;

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Bean(String title, String desc, String time, String phone) {
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
