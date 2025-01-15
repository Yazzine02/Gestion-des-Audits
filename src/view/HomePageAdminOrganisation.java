package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageAdminOrganisation extends JFrame implements ActionListener {
    // Panels
    private JPanel mainPanel;
    private JPanel dashboardPanel;
    private JPanel contentPanel;
    // Navigation Buttons
    private JButton organisationButton;
    private JButton siteButton;
    private JButton processusButton;
    private JButton standardsButton;
    private JButton clausesButton;
    private JButton systemeDeManagementButton;
    private JButton actionButton;
    private JButton correctionButton;
    private JButton auditButton;
    private JButton profileButton;

    // Color Palette
    private final Color PRIMARY_COLOR = new Color(33, 150, 243); // Modern Blue
    private final Color SECONDARY_COLOR = new Color(63, 81, 181); // Deep Blue
    private final Color BACKGROUND_COLOR = new Color(240, 242, 245); // Light Gray
    private final Color TEXT_COLOR = Color.WHITE;

    public HomePageAdminOrganisation() {
        setTitle("Admin Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialization
        initializeSideBar();
        initializeMainContent();
    }

    private void initializeSideBar() {
        // Sidebar configuration
        dashboardPanel = new JPanel();
        dashboardPanel.setBackground(SECONDARY_COLOR);
        dashboardPanel.setPreferredSize(new Dimension(250, getHeight()));
        dashboardPanel.setLayout(new BorderLayout());

        // Logo section
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(SECONDARY_COLOR);
        logoPanel.setPreferredSize(new Dimension(250, 150));

        // Load and scale the logo
        java.net.URL imgUrl = getClass().getResource("audit.png");
        if (imgUrl != null) {
            ImageIcon logoImage = new ImageIcon(imgUrl);
            Image scaledLogoImage = logoImage.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogoImage));
            logoPanel.add(logoLabel);
        }

        // Navigation buttons
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(SECONDARY_COLOR);
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        organisationButton = createStyledButton("Organisation");
        navigationPanel.add(organisationButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        siteButton = createStyledButton("Site");
        navigationPanel.add(siteButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        processusButton = createStyledButton("Processus");
        navigationPanel.add(processusButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        standardsButton = createStyledButton("Standards");
        navigationPanel.add(standardsButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        clausesButton = createStyledButton("Clauses");
        navigationPanel.add(clausesButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        systemeDeManagementButton = createStyledButton("Systeme De-Management");
        navigationPanel.add(systemeDeManagementButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        profileButton = createStyledButton("Profile");
        navigationPanel.add(profileButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        actionButton = createStyledButton("Action");
        navigationPanel.add(actionButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        correctionButton = createStyledButton("Correction");
        navigationPanel.add(correctionButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        auditButton = createStyledButton("Audit");
        navigationPanel.add(auditButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Assemble sidebar
        dashboardPanel.add(logoPanel, BorderLayout.NORTH);
        dashboardPanel.add(navigationPanel, BorderLayout.CENTER);

        // Add sidebar to frame
        add(dashboardPanel, BorderLayout.WEST);
    }

    // Custom button creation method
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);

        // Consistent button styling
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        // Consistent size
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setMinimumSize(new Dimension(200, 45));
        button.setPreferredSize(new Dimension(200, 45));

        // Remove button border and focus painting
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(SECONDARY_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        // Add action listener
        button.addActionListener(this);

        return button;
    }

    private void initializeMainContent() {
        mainPanel = new JPanel();
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Dashboard", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(33, 33, 33));

        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==organisationButton) {
            // Close window
            this.dispose();
            // open new window
            SwingUtilities.invokeLater(()->{
                try{
                    // Make the system lookandfeel stay consistent throughout the app
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    // Create and show dashboard
                    OrganisationAdminOrganisation dashboard = new OrganisationAdminOrganisation();
                    dashboard.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening organisation: "+ex.getMessage());
                }
            });
        }
        else if (e.getSource()==siteButton) {
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    SiteAdminOrganisation dashboard = new SiteAdminOrganisation();
                    dashboard.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening site: "+ex.getMessage());
                }
            });
        }
        else if (e.getSource()==processusButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    ProcessusAdminOrganisation dashboard = new ProcessusAdminOrganisation();
                    dashboard.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening processus: "+ex.getMessage());
                }
            });
        }
        else if(e.getSource()==standardsButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    StandardAdminOrganisation dashboard = new StandardAdminOrganisation();
                    dashboard.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening standard: "+ex.getMessage());
                }
            });
        }
        else if (e.getSource()==clausesButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    ClauseAdminOrganisation dashboard = new ClauseAdminOrganisation();
                    dashboard.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening clause: "+ex.getMessage());
                }
            });
        }
        else if(e.getSource()==actionButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    ActionAdminOrganisation actionAdminOrganisation = new ActionAdminOrganisation();
                    actionAdminOrganisation.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening action: "+ex.getMessage());
                }
            });
        }
        else if(e.getSource()==correctionButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    CorrectionAdminOrganisation correctionAdminOrganisation = new CorrectionAdminOrganisation();
                    correctionAdminOrganisation.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening correction: "+ex.getMessage());
                }
            });
        }
        else if(e.getSource()==auditButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    AuditAdminOrganisation auditAdminOrganisation = new AuditAdminOrganisation();
                    auditAdminOrganisation.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening audit: "+ex.getMessage());
                }
            });
        }
        else if (e.getSource()==systemeDeManagementButton){
            this.dispose();
            SwingUtilities.invokeLater(()->{
                try{
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    SystemeDeManagementAdminOrganisation systemeDeManagementAdminOrganisation = new SystemeDeManagementAdminOrganisation();
                    systemeDeManagementAdminOrganisation.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening systeme de management: "+ex.getMessage());
                }
            });
        }
    }
}