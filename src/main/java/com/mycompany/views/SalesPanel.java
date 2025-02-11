package com.mycompany.views;

import javax.swing.*;
import java.awt.*;

public class SalesPanel extends JPanel {
    public SalesPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Sales Management", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> mainFrame.switchPanel("Menu"));
        // Embed ManageUserView
        SaleViewPanel saleViewPanel = new SaleViewPanel();

        // Add components
        add(label, BorderLayout.NORTH);
        add(saleViewPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
