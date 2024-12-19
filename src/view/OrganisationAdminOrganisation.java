package view;

import controller.OrganisationController;
import model.Organisation;

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
    // Table
    private JTable table;
    private DefaultTableModel tableModel;
    // Data field
    private List<Organisation> organisations;
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

        JButton addButton = new JButton("Add Organisation");
        addButton.setBackground(PRIMARY_COLOR);
        addButton.setForeground(TEXT_COLOR);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        addButton.setMinimumSize(new Dimension(200, 45));
        addButton.setPreferredSize(new Dimension(200, 45));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);

        // Column names
        String[] columnNames = {"ID", "Name", "Address","Actions"};

        // Get organisations
        organisations = OrganisationController.getAllOrganisations();

        // Make the table non-editable
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Populate the table directly
        for(Organisation org : organisations){
            tableModel.addRow(new Object[]{
                    org.getId(),
                    org.getName(),
                    org.getAddress(),
                    "Actions"
            });
        }

        // Initialize JTable with the table model
        table = new JTable(tableModel);
        // Set up the Actions column with buttons
        // We will create custom buttons using the ButtonRenderer class
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
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

                    Organisation selectedOrg = organisations.get(row);
                    if (relativeX > firstButtonX && relativeX < firstButtonX + buttonWidth) {
                        modifyOrganisation(selectedOrg, row);
                    } else if (relativeX > firstButtonX + buttonWidth + padding) {
                        deleteOrganisation(selectedOrg);
                    }
                }
            }
        });
        // Add table to a scroll pane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the Add button and the table
        mainPanel.add(addButton, BorderLayout.NORTH);
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

    // Modify an organisation method
    private void modifyOrganisation(Organisation org, int row){
        // Create custom dialog
        JDialog dialog = new JDialog(this, "Edit Organisation", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(org.getName());
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(org.getAddress());

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.setBackground(PRIMARY_COLOR);
        confirmButton.setForeground(TEXT_COLOR);

        // Add action listeners
        confirmButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newAddress = addressField.getText().trim();

            if (newName.isEmpty() || newAddress.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                        "Name and address cannot be empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update organisation
            org.setName(newName);
            org.setAddress(newAddress);
            OrganisationController.updateOrganisation(org.getId(), org.getName(), org.getAddress());

            // Update table
            tableModel.setValueAt(newName, row, 1);
            tableModel.setValueAt(newAddress, row, 2);

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
    // Remove an organisation method
    private void deleteOrganisation(Organisation org){
        // JOptionPan.YES_NO_OPTION is an integer
        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete "+org.getName()+"?","Confirm",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            // Remove using the controller
            OrganisationController.deleteOrganisation(org.getId());
            tableModel.removeRow(organisations.indexOf(org));
            organisations.remove(org);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}