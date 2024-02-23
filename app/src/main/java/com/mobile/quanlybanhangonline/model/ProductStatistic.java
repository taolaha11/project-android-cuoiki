package com.mobile.quanlybanhangonline.model;

public class ProductStatistic {
    private String productName;
    private int quantity;
    private double totalSales;

    public ProductStatistic(String productName, int quantity, double totalSales) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalSales = totalSales;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalSales() {
        return totalSales;
    }
}
