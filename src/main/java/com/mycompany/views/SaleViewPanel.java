package com.mycompany.views;

import com.mycompany.services.SaleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.math.BigDecimal;

public class SaleViewPanel extends JPanel {
    private final SaleService saleService;
    private JTextField furnitureIdField;
    private JTextField quantityField;
    private JTextField totalPriceField;
    private JTable salesTable;
    private DefaultTableModel tableModel;

    public SaleViewPanel() {
        saleService = new SaleService();
        setLayout(new BorderLayout());

        // Panel for sale details
        JPanel salePanel = new JPanel();
        salePanel.setLayout(new GridLayout(4, 2));

        JLabel furnitureIdLabel = new JLabel("Furniture ID:");
        furnitureIdField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity Sold:");
        quantityField = new JTextField();

        JLabel totalPriceLabel = new JLabel("Total Price:");
        totalPriceField = new JTextField();

        JButton recordSaleButton = new JButton("Record Sale");

        salePanel.add(furnitureIdLabel);
        salePanel.add(furnitureIdField);
        salePanel.add(quantityLabel);
        salePanel.add(quantityField);
        salePanel.add(totalPriceLabel);
        salePanel.add(totalPriceField);
        salePanel.add(recordSaleButton);

        add(salePanel, BorderLayout.NORTH);

        // Table for showing all sales
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Sale ID");
        tableModel.addColumn("Furniture ID");
        tableModel.addColumn("Quantity Sold");
        tableModel.addColumn("Total Price");
        tableModel.addColumn("Sale Date");

        salesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(salesTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load existing sales
        loadSales();

        // Action listener for "Record Sale" button
        recordSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int furnitureId = Integer.parseInt(furnitureIdField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    double totalPrice = Double.parseDouble(totalPriceField.getText());
                    
                    // Record the sale
                    int result = saleService.recordSale(furnitureId, quantity, totalPrice);
                    if (result > 0) {
                        // Update stock
                        saleService.updateStockAfterSale(furnitureId, quantity);

                        // Clear input fields
                        furnitureIdField.setText("");
                        quantityField.setText("");
                        totalPriceField.setText("");

                        // Load updated sales
                        loadSales();

                        JOptionPane.showMessageDialog(SaleViewPanel.this, "Sale recorded and stock updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SaleViewPanel.this, "Error recording sale", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SaleViewPanel.this, "Please enter valid numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadSales() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Get sales from service
        List<Map<String, Object>> sales = saleService.getAllSales();

        // Add sales to table model
        for (Map<String, Object> saleData : sales) {
            int id = (int) saleData.get("id");
            int furnitureId = (int) saleData.get("furniture_id");
            int quantity = (int) saleData.get("quantity");
            BigDecimal totalpriceBigDecimal = (BigDecimal) saleData.get("total_price");
            double totalPrice = totalpriceBigDecimal.doubleValue();
            Date saleDate = (Date) saleData.get("sale_date");

            // Format sale date
            String formattedDate = saleDate.toString();

            tableModel.addRow(new Object[]{id, furnitureId, quantity, totalPrice, formattedDate});
        }
    }
}
