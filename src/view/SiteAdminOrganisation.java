package view;

import controller.OrganisationController;
import controller.SiteController;
import model.Site;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SiteAdminOrganisation extends JFrame implements ActionListener {
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
    // Table
    private JTable table;
    private DefaultTableModel tableModel;
    // Data field
    private List<Site> sites;
    // Color Palette
    private final Color PRIMARY_COLOR = new Color(33, 150, 243); // Modern Blue
    private final Color SECONDARY_COLOR = new Color(63, 81, 181); // Deep Blue
    private final Color BACKGROUND_COLOR = new Color(240, 242, 245); // Light Gray
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color ADD_BUTTON = new Color(15,150,43);

    public SiteAdminOrganisation() {
        setTitle("Site Admin Organisation");
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

        // Create a panel for the Add button with FlowLayout aligned to the left
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        // The add button
        JButton addButton = new JButton("Add Site");
        addButton.setBackground(ADD_BUTTON);
        addButton.setForeground(TEXT_COLOR);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setPreferredSize(new Dimension(200, 45));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        // Add hover effect
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                addButton.setBackground(ADD_BUTTON.darker());
            }
            public void mouseExited(MouseEvent evt) {
                addButton.setBackground(ADD_BUTTON);
            }
        });
        // Add action listen to add method
        addButton.addActionListener(e -> addSite());
        buttonPanel.add(addButton);
        // Add the button panel to the NORTH of the main panel
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Column names
        String[] columnNames = {"Adresse", "Description","Name","Actions"};

        // Get organisations
        sites = SiteController.getSites();

        // Make the table non-editable
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Initialize JTable with the table model
        table = new JTable(tableModel);
        // Populate the table directly
        if(!sites.isEmpty()){
            for(Site site : sites){
                tableModel.addRow(new Object[]{
                        site.getAdresse(),
                        site.getDescription(),
                        site.getName(),
                        "Actions"
                });
            }
        }
        // Setting heights of all rows of the table
        table.setRowHeight(50);
        // Set up the Actions column with buttons
        // We will create custom buttons using the ButtonRenderer class
        table.getColumnModel().getColumn(3).setCellRenderer(new SiteAdminOrganisation.ButtonRenderer());
        // Adding mouse listeners
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                if (row < table.getRowCount() && row >= 0 && column == 3) {
                    // Get click coordinates inside the cell
                    int buttonWidth = 60;
                    int padding = 5;
                    int firstButtonX = (table.getColumnModel().getColumn(column).getWidth() - (2 * buttonWidth + padding)) / 2;

                    // Calculate if Edit or Delete button was clicked
                    int relativeX = e.getX() - table.getColumnModel().getColumn(column).getWidth() * 3; // Adjust for previous columns

                    Site selectedSite = sites.get(row);
                    if (relativeX > firstButtonX && relativeX < firstButtonX + buttonWidth) {
                        modifySite(selectedSite, row);
                    } else if (relativeX > firstButtonX + buttonWidth + padding) {
                        deleteSite(selectedSite);
                    }
                }
            }
        });

        // Add table to a scroll pane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }
    // Custom Button class for the Actions column of the table
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            removeAll();

            JButton editBtn = new JButton("Edit");
            JButton deleteBtn = new JButton("Delete");

            editBtn.setBackground(PRIMARY_COLOR);
            editBtn.setForeground(TEXT_COLOR);
            deleteBtn.setBackground(Color.RED);
            deleteBtn.setForeground(TEXT_COLOR);

            add(editBtn);
            add(deleteBtn);

            return this;
        }
    }
    // Add method
    void addSite() {
        // Create custom dialog
        JDialog dialog = new JDialog(this, "Add Site", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add form fields
        JLabel adresseLabel = new JLabel("Adresse:");
        JTextField adresseField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description");
        JTextField descriptionField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        formPanel.add(adresseLabel);
        formPanel.add(adresseField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.setBackground(PRIMARY_COLOR);
        confirmButton.setForeground(TEXT_COLOR);

        // Add action listeners
        confirmButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newAdresse = adresseField.getText().trim();
            String newDescription = descriptionField.getText().trim();

            if (newName.isEmpty() || newAdresse.isEmpty() || newDescription.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                        "Name, address, description cannot be empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Adding site
            Site site = new Site(newAdresse, newDescription, newName);
            SiteController.addSite(site);

            sites.add(site);
            tableModel.addRow(new Object[]{site.getAdresse(),site.getDescription(), site.getName(), "Actions"});

            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add panels to dialog
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    // Modify method
    private void modifySite(Site site, int row){
        // Create custom dialog
        JDialog dialog = new JDialog(this, "Edit Site", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add form fields
        JLabel adresseLabel = new JLabel("Adresse:");
        JTextField adresseField = new JTextField(site.getAdresse());
        JLabel descriptionLabel = new JLabel("Description");
        JTextField descriptionField = new JTextField(site.getDescription());
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(site.getName());

        formPanel.add(adresseLabel);
        formPanel.add(adresseField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.setBackground(PRIMARY_COLOR);
        confirmButton.setForeground(TEXT_COLOR);

        // Add action listeners
        confirmButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newAddress = adresseField.getText().trim();
            String newDescription = descriptionField.getText().trim();

            if (newName.isEmpty() || newAddress.isEmpty() || newDescription.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                        "Name, adresse and description cannot be empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update organisation
            site.setAdresse(newAddress);
            site.setName(newName);
            site.setDescription(newDescription);
            SiteController.updateSite(site.getId(),site.getAdresse(),site.getDescription(),site.getName());

            // Update table
            tableModel.setValueAt(newAddress, row, 0);
            tableModel.setValueAt(newDescription, row, 1);
            tableModel.setValueAt(newName, row, 2);

            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add panels to dialog
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
    // Remove method
    private void deleteSite(Site site){
        // JOptionPan.YES_NO_OPTION is an integer
        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete "+site.getName()+"?","Confirm",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            // Remove using the controller
            OrganisationController.deleteOrganisation(site.getId());
            tableModel.removeRow(sites.indexOf(site));
            sites.remove(site);
        }
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
