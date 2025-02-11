package com.mycompany.views;

import com.mycompany.services.UserService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class ManageUserView extends JPanel {  // Change from JFrame to JPanel
    private final UserService userService;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public ManageUserView() {
        userService = new UserService();
        setLayout(new BorderLayout());

        // Create table model and table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Role");
        tableModel.addColumn("Status");

        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load users into the table
        loadUsers();

        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton blockButton = new JButton("Block");
        JButton reactivateButton = new JButton("Reactivate");
        JButton addButton = new JButton("Add User");

        buttonPanel.add(blockButton);
        buttonPanel.add(reactivateButton);
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        blockButton.addActionListener((ActionEvent e) -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int userId = (int) tableModel.getValueAt(selectedRow, 0);
                userService.blockUser(userId);
                loadUsers();
                JOptionPane.showMessageDialog(this, "User blocked successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        reactivateButton.addActionListener((ActionEvent e) -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int userId = (int) tableModel.getValueAt(selectedRow, 0);
                userService.reactivateUser(userId);
                loadUsers();
                JOptionPane.showMessageDialog(this, "User reactivated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addButton.addActionListener((ActionEvent e) -> {
            String username = JOptionPane.showInputDialog(this, "Enter username:");
            String password = JOptionPane.showInputDialog(this, "Enter password:");
            String role = JOptionPane.showInputDialog(this, "Enter role:");

            if (username != null && password != null && role != null) {
                userService.addUser(username, password, role);
                loadUsers();
                JOptionPane.showMessageDialog(this, "User added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void loadUsers() {
        tableModel.setRowCount(0); // Clear table
        List<Map<String, Object>> users = userService.getAllUsers();
        for (Map<String, Object> user : users) {
            tableModel.addRow(new Object[]{
                user.get("id"),
                user.get("username"),
                user.get("role"),
                user.get("status")
            });
        }
    }
}
