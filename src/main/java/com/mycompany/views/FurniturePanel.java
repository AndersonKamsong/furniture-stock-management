package com.mycompany.views;

import javax.swing.*;
import java.awt.*;

public class FurniturePanel extends JPanel {
    public FurniturePanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Furniture Management", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> mainFrame.switchPanel("Menu"));
        // Embed ManageUserView
        FurnitureViewPanel furnitureViewPanel = new FurnitureViewPanel();

        // Add components
        add(label, BorderLayout.NORTH);
        add(furnitureViewPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
