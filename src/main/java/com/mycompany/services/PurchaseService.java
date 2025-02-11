package com.mycompany.services;

import com.mycompany.config.DataSource;
import com.mycompany.model.Purchase;
import java.util.List;
import java.util.Map;

public class PurchaseService {
    private final DataSource dataSource;

    public PurchaseService() {
        this.dataSource = new DataSource();
    }

    // Track a furniture purchase and update stock
    public boolean recordPurchase(Purchase purchase) {
        String purchaseQuery = "INSERT INTO purchases (furniture_id, quantity, total_cost, purchase_date) VALUES (?, ?, ?, NOW())";
        String stockQuery = "UPDATE furniture SET stock = stock + ? WHERE id = ?";
        
        boolean purchaseSuccess = dataSource.insert(purchaseQuery, 
            purchase.getFurnitureId(), 
            purchase.getQuantity(), 
            purchase.getTotalCost()
        ) > 0;

        boolean stockUpdateSuccess = dataSource.update(stockQuery, 
            purchase.getQuantity(), 
            purchase.getFurnitureId()
        ) > 0;

        return purchaseSuccess && stockUpdateSuccess;
    }
    
    public List<Map<String, Object>> getAllPurchases() {
        return dataSource.select("SELECT * FROM purchases");
    }
}
