package com.mycompany.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public MenuPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton userButton = new JButton("User Management");
        JButton furnitureButton = new JButton("Furniture");
        JButton salesButton = new JButton("Sales");
        JButton purchaseButton = new JButton("Purchase");
        JButton logoutButton = new JButton("Logout");

        userButton.addActionListener(e -> mainFrame.switchPanel("UserManagement"));
        furnitureButton.addActionListener(e -> mainFrame.switchPanel("Furniture"));
        salesButton.addActionListener(e -> mainFrame.switchPanel("Sales"));
        purchaseButton.addActionListener(e -> mainFrame.switchPanel("Purchase"));
        logoutButton.addActionListener(e -> mainFrame.switchPanel("Login"));

        add(userButton);
        add(furnitureButton);
        add(salesButton);
        add(purchaseButton);
        add(logoutButton);
    }
}
