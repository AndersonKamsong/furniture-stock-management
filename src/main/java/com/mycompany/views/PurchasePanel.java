package com.mycompany.views;

import javax.swing.*;
import java.awt.*;

public class PurchasePanel extends JPanel {
    public PurchasePanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Purchase Management", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> mainFrame.switchPanel("Menu"));
        // Embed ManageUserView
        PurchaseViewPanel purchaseViewPanel = new PurchaseViewPanel();

        // Add components
        add(label, BorderLayout.NORTH);
        add(purchaseViewPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
