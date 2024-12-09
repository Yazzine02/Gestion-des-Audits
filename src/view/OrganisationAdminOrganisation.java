package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganisationAdminOrganisation extends JFrame implements ActionListener {
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
    private JButton profileButton;
    // Color Palette
    private final Color PRIMARY_COLOR = new Color(33, 150, 243); // Modern Blue
    private final Color SECONDARY_COLOR = new Color(63, 81, 181); // Deep Blue
    private final Color BACKGROUND_COLOR = new Color(240, 242, 245); // Light Gray
    private final Color TEXT_COLOR = Color.WHITE;

    public OrganisationAdminOrganisation() {
        setTitle("Organisation Admin Organisation");
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

        JLabel welcomeLabel = new JLabel("Insert content here", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(33, 33, 33));

        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
