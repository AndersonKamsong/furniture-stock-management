package com.mycompany.services;

import com.mycompany.config.DataSource;
import java.util.List;
import java.util.Map;

public class FurnitureService {
    private final DataSource dataSource;

    public FurnitureService() {
        this.dataSource = new DataSource();
    }

    public List<Map<String, Object>> getAllFurniture() {
        return dataSource.select("SELECT * FROM furniture");
    }

    public Map<String, Object> getFurnitureById(int id) {
        List<Map<String, Object>> result = dataSource.select("SELECT * FROM furniture WHERE id = ?", id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int addFurniture(String name, String category, int quantity, double price) {
        return dataSource.insert("INSERT INTO furniture (name, category, stock, price) VALUES (?, ?, ?, ?)", name, category, quantity, price);
    }

    public int updateFurniture(int id, String name, String type, int quantity, double price) {
        return dataSource.update("UPDATE furniture SET name = ?, category = ?, stock = ?, price = ? WHERE id = ?", name, type, quantity, price, id);
    }

    public int deleteFurniture(int id) {
        return dataSource.delete("DELETE FROM furniture WHERE id = ?", id);
    }

    public List<Map<String, Object>> getLowStockFurniture(int threshold) {
        return dataSource.select("SELECT * FROM furniture WHERE stock <= ?", threshold);
    }
}
