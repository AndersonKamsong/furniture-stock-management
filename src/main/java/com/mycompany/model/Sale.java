package com.mycompany.model;

import java.util.Date;

public class Sale {
    private int id;
    private int furnitureId;
    private int quantity;
    private double totalPrice;
    private Date saleDate;

    public Sale(int id, int furnitureId, int quantity, double totalPrice, Date saleDate) {
        this.id = id;
        this.furnitureId = furnitureId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFurnitureId() { return furnitureId; }
    public void setFurnitureId(int furnitureId) { this.furnitureId = furnitureId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public Date getSaleDate() { return saleDate; }
    public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }
}
