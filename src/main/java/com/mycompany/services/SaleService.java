package com.mycompany.services;

import com.mycompany.config.DataSource;
import java.util.List;
import java.util.Map;

public class SaleService {
    private final DataSource dataSource;

    public SaleService() {
        this.dataSource = new DataSource();
    }

    public int recordSale(int furnitureId, int quantity, double totalPrice) {
        return dataSource.insert("INSERT INTO sales (furniture_id, quantity, total_price) VALUES (?, ?, ?)", furnitureId, quantity, totalPrice);
    }

    public int updateStockAfterSale(int furnitureId, int quantity) {
        return dataSource.update("UPDATE furniture SET stock = stock - ? WHERE id = ?", quantity, furnitureId);
    }

    public List<Map<String, Object>> getAllSales() {
        return dataSource.select("SELECT * FROM sales");
    }
}
