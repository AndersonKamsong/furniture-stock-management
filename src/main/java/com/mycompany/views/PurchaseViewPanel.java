package com.mycompany.views;

import com.mycompany.services.PurchaseService;
import com.mycompany.model.Purchase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.math.BigDecimal;

public class PurchaseViewPanel extends JPanel {
    private final PurchaseService purchaseService;
    private JTextField furnitureIdField;
    private JTextField quantityField;
    private JTextField totalCostField;
    private JTable purchaseTable;
    private DefaultTableModel tableModel;

    public PurchaseViewPanel() {
        purchaseService = new PurchaseService();
        setLayout(new BorderLayout());

        JPanel purchasePanel = new JPanel();
        purchasePanel.setLayout(new GridLayout(4, 2));

        JLabel furnitureIdLabel = new JLabel("Furniture ID:");
        furnitureIdField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();

        JLabel totalCostLabel = new JLabel("Total Cost:");
        totalCostField = new JTextField();

        JButton recordPurchaseButton = new JButton("Record Purchase");

        purchasePanel.add(furnitureIdLabel);
        purchasePanel.add(furnitureIdField);
        purchasePanel.add(quantityLabel);
        purchasePanel.add(quantityField);
        purchasePanel.add(totalCostLabel);
        purchasePanel.add(totalCostField);
        purchasePanel.add(recordPurchaseButton);

        add(purchasePanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Furniture ID");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Total Cost");
        tableModel.addColumn("Purchase Date");

        purchaseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(purchaseTable);
        add(scrollPane, BorderLayout.CENTER);

        loadPurchases();

        recordPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int furnitureId = Integer.parseInt(furnitureIdField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    double totalCost = Double.parseDouble(totalCostField.getText());
                    Purchase purchase = new Purchase(0, furnitureId, quantity, totalCost, new Date());

                    if (purchaseService.recordPurchase(purchase)) {
                        furnitureIdField.setText("");
                        quantityField.setText("");
                        totalCostField.setText("");
                        JOptionPane.showMessageDialog(PurchaseViewPanel.this, "Purchase recorded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadPurchases();
                    } else {
                        JOptionPane.showMessageDialog(PurchaseViewPanel.this, "Error recording purchase", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PurchaseViewPanel.this, "Please enter valid numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadPurchases() {
        tableModel.setRowCount(0);
        List<Map<String, Object>> purchases = purchaseService.getAllPurchases();
        for (Map<String, Object> purchaseData : purchases) {
            int id = (int) purchaseData.get("id");
            int furnitureId = (int) purchaseData.get("furniture_id");
            int quantity = (int) purchaseData.get("quantity");
            BigDecimal totalPriceBigDecimal = (BigDecimal) purchaseData.get("total_cost");
            double totalPrice = totalPriceBigDecimal.doubleValue();
            Date purchaseDate = (Date) purchaseData.get("purchase_date");
            String formattedDate = purchaseDate.toString();
            tableModel.addRow(new Object[]{id, furnitureId, quantity, totalPrice, formattedDate});
        }
    }
}
