package com.mobile.quanlybanhangonline.model;

import java.io.Serializable;

public class BillStatistic implements Serializable {
    private int idBill;
    private String userName;
    private float totalBill;
    private String description;
    private String datetime;

    public BillStatistic(int idBill, String userName, float totalBill, String description, String datetime) {
        this.idBill = idBill;
        this.userName = userName;
        this.totalBill = totalBill;
        this.description = description;
        this.datetime = datetime;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getIdBill() {
        return idBill;
    }

    public String getUserName() {
        return userName;
    }

    public float getTotalBill() {
        return totalBill;
    }

    public String getDescription() {
        return description;
    }

    public String getDatetime() {
        return datetime;
    }
}
