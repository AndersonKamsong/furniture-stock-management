package com.mycompany.views;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel containerPanel;
    
    public MainFrame() {
        setTitle("Furniture Store Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create CardLayout for panel switching
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);

        // Create panels
        LoginPanel loginPanel = new LoginPanel(this);
        MenuPanel menuPanel = new MenuPanel(this);
        UserManagementPanel userPanel = new UserManagementPanel(this);
        SalesPanel salesPanel = new SalesPanel(this);
        FurniturePanel furniturePanel = new FurniturePanel(this);
        PurchasePanel purchasePanel = new PurchasePanel(this);

        // Add panels to the container
        containerPanel.add(loginPanel, "Login");
        containerPanel.add(menuPanel, "Menu");
        containerPanel.add(userPanel, "UserManagement");
        containerPanel.add(furniturePanel, "Furniture");
        containerPanel.add(salesPanel, "Sales");
        containerPanel.add(purchasePanel, "Purchase");

        add(containerPanel);
        cardLayout.show(containerPanel, "Login");  // Show login first
    }

    // Method to switch panels
    public void switchPanel(String panelName) {
        cardLayout.show(containerPanel, panelName);
    }

    // **Main function**
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         MainFrame mainFrame = new MainFrame();
    //         mainFrame.setVisible(true);
    //     });
    // }
}
