package com.mycompany.views;

import com.mycompany.services.FurnitureService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public class FurnitureViewPanel extends JPanel {
    private final FurnitureService furnitureService;
    private JTable furnitureTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, categoryField, priceField, stockField;

    public FurnitureViewPanel() {
        furnitureService = new FurnitureService();
        setLayout(new BorderLayout());
        initUI();
        loadFurnitureData();
    }

    private void initUI() {
        JPanel addFurniturePanel = new JPanel(new GridLayout(5, 2, 10, 10));

        addFurniturePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        addFurniturePanel.add(nameField);

        addFurniturePanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        addFurniturePanel.add(categoryField);

        addFurniturePanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        addFurniturePanel.add(priceField);

        addFurniturePanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        addFurniturePanel.add(stockField);

        JButton addButton = new JButton("Add Furniture");
        addFurniturePanel.add(addButton);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Stock"}, 0);
        furnitureTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(furnitureTable);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(addFurniturePanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this::addFurniture);
        updateButton.addActionListener(this::updateFurniture);
        deleteButton.addActionListener(this::deleteFurniture);
    }

    private void loadFurnitureData() {
        List<Map<String, Object>> furnitureList = furnitureService.getAllFurniture();
        tableModel.setRowCount(0);
        for (Map<String, Object> furnitureData : furnitureList) {
            tableModel.addRow(new Object[]{
                    furnitureData.get("id"),
                    furnitureData.get("name"),
                    furnitureData.get("category"),
                    furnitureData.get("price"),
                    furnitureData.get("stock")
            });
        }
    }

    private void addFurniture(ActionEvent e) {
        String name = nameField.getText();
        String category = categoryField.getText();
        String priceText = priceField.getText();
        String stockText = stockField.getText();

        if (name.isEmpty() || category.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = Double.parseDouble(priceText);
        int stock = Integer.parseInt(stockText);

        furnitureService.addFurniture(name, category, stock, price);
        loadFurnitureData();
        JOptionPane.showMessageDialog(this, "Furniture added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateFurniture(ActionEvent e) {
        int selectedRow = furnitureTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) furnitureTable.getValueAt(selectedRow, 0);
            String name = (String) furnitureTable.getValueAt(selectedRow, 1);
            String type = (String) furnitureTable.getValueAt(selectedRow, 2);
            BigDecimal priceBigDecimal = (BigDecimal) furnitureTable.getValueAt(selectedRow, 3);
            double price = priceBigDecimal.doubleValue();
            int stock = (int) furnitureTable.getValueAt(selectedRow, 4);

            String newName = JOptionPane.showInputDialog(this, "Enter new name", name);
            String newType = JOptionPane.showInputDialog(this, "Enter new type", type);
            String newPriceText = JOptionPane.showInputDialog(this, "Enter new price", price);
            String newStockText = JOptionPane.showInputDialog(this, "Enter new stock", stock);

            double newPrice = Double.parseDouble(newPriceText);
            int newStock = Integer.parseInt(newStockText);

            furnitureService.updateFurniture(id, newName, newType, newStock, newPrice);
            loadFurnitureData();
            JOptionPane.showMessageDialog(this, "Furniture updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteFurniture(ActionEvent e) {
        int selectedRow = furnitureTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) furnitureTable.getValueAt(selectedRow, 0);
            furnitureService.deleteFurniture(id);
            loadFurnitureData();
            JOptionPane.showMessageDialog(this, "Furniture deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
