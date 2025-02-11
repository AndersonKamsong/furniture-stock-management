package com.mycompany.model;

import java.util.Date;

public class Purchase {
    private int id;
    private int furnitureId;
    private int quantity;
    private double totalCost;
    private Date purchaseDate;

    public Purchase(int id, int furnitureId, int quantity, double totalCost, Date purchaseDate) {
        this.id = id;
        this.furnitureId = furnitureId;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFurnitureId() { return furnitureId; }
    public void setFurnitureId(int furnitureId) { this.furnitureId = furnitureId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }
}
