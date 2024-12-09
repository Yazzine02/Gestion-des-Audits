package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.UserCredentialManager.verifyLogin;

public class Login extends JFrame implements ActionListener {
    JPanel panel;
    JLabel userLabel, passwordLabel, authenticationMessage;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;

    public Login() {
        // Username
        userLabel = new JLabel("Username");
        usernameField = new JTextField(20);
        // Password
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(20);
        // Buttons
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        // Message
        authenticationMessage = new JLabel();
        // Panel and grid
        panel = new JPanel(new GridLayout(4,2));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);
        panel.add(authenticationMessage);
        add(panel, BorderLayout.CENTER);
        // Listeners
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        // Parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Authentication");
        setSize(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton){
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if(verifyLogin(username, password)){
                // Should add how to get role of the user then display the correct screen
                authenticationMessage.setText("Login Successful");
                authenticationMessage.setForeground(Color.GREEN);
                // Close login window
                this.dispose();
                // Open Dashboard
                SwingUtilities.invokeLater(()->{
                   try{
                       // Make the system lookandfeel stay consistent throughout the app
                       UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                       // Create and show dashboard
                       HomePageAdminOrganisation dashboard = new HomePageAdminOrganisation();
                       dashboard.setVisible(true);
                   }catch(Exception ex){
                       ex.printStackTrace();
                       JOptionPane.showMessageDialog(null, "Error opening dashboard: "+ex.getMessage()+" Dashboard error");
                   }
                });
            }
            else{
                authenticationMessage.setText("Login Failed");
                authenticationMessage.setForeground(Color.RED);
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }
}
