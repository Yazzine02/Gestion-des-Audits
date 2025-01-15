package view;

import controller.AuditController;
import model.Audit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AuditAuditeur extends JFrame implements ActionListener {
    // Panels
    private JPanel mainPanel;
    // Table
    private JTable table;
    private DefaultTableModel tableModel;
    // Data field
    private List<Audit> audits;
    // Color Palette
    private final Color PRIMARY_COLOR = new Color(33, 150, 243); // Modern Blue
    private final Color SECONDARY_COLOR = new Color(63, 81, 181); // Deep Blue
    private final Color BACKGROUND_COLOR = new Color(240, 242, 245); // Light Gray
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color ADD_BUTTON = new Color(15,150,43);

    private JButton logoutButton;


    public AuditAuditeur() {
        setTitle("Audit Auditeur");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeMainContent();
        addLogoutButton();
    }
    private void addLogoutButton() {
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(TEXT_COLOR);
        logoutButton.addActionListener(e -> performLogout());

        // Create a panel for the logout button and align it to the right
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setBackground(BACKGROUND_COLOR);
        logoutPanel.add(logoutButton);

        // Add the logout panel to the top of the frame
        if (mainPanel != null) {
            // Get the existing button panel
            Component northComponent = ((BorderLayout) mainPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
            if (northComponent instanceof JPanel) {
                JPanel existingButtonPanel = (JPanel) northComponent;
                // Add the logout button to the existing panel
                existingButtonPanel.add(logoutButton);
            }
        }
    }

    private void performLogout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Close the current window
            this.dispose();

            // Open the login window
            SwingUtilities.invokeLater(() -> {
                try {
                    // Make sure the look and feel stays consistent
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                    // Create and show login window
                    Login loginWindow = new Login();
                    loginWindow.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            "Error opening login window: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }
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

            JButton editBtn = new JButton("Lancer");
            JButton deleteBtn = new JButton("Cloturer");

            editBtn.setBackground(PRIMARY_COLOR);
            editBtn.setForeground(TEXT_COLOR);
            deleteBtn.setBackground(Color.RED);
            deleteBtn.setForeground(TEXT_COLOR);

            add(editBtn);
            add(deleteBtn);

            return this;
        }
    }

    private void initializeMainContent() {
        mainPanel = new JPanel();
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setLayout(new BorderLayout());

        // Create a panel for the Add button with FlowLayout aligned to the left
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        // Add the button panel to the NORTH of the main panel
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Column names
        String[] columnNames = {"Date Debut", "Date Fin","Intitule","Status","Systeme exigence","Type", "Auditeur ID","Actions"};

        // Get organisations
        audits = AuditController.getAllAudits();

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
        if(!audits.isEmpty()){
            for(Audit audit : audits){
                tableModel.addRow(new Object[]{
                        audit.getDateDebut(),
                        audit.getDateFin(),
                        audit.getIntitule(),
                        audit.getStatus(),
                        audit.getSystemeExigenceId(),
                        audit.getType(),
                        audit.getAuditeurId(),
                        "Actions"
                });
            }
        }
        // Setting heights of all rows of the table
        table.setRowHeight(50);
        // Set up the Actions column with buttons
        // We will create custom buttons using the ButtonRenderer class
        table.getColumnModel().getColumn(7).setCellRenderer(new AuditAuditeur.ButtonRenderer());
        // Adding mouse listeners
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                if (row < table.getRowCount() && row >= 0 && column == 7) {
                    // Get click coordinates inside the cell
                    int buttonWidth = 60;
                    int padding = 5;
                    int firstButtonX = (table.getColumnModel().getColumn(column).getWidth() - (2 * buttonWidth + padding)) / 2;

                    // Calculate if Edit or Delete button was clicked
                    int relativeX = e.getX() - table.getColumnModel().getColumn(column).getWidth() * 7; // Adjust for previous columns

                    Audit selectedAudit = audits.get(row);
                    if (relativeX > firstButtonX && relativeX < firstButtonX + buttonWidth) {
                        lancerAudit(selectedAudit, row);
                    } else if (relativeX > firstButtonX + buttonWidth + padding) {
                        cloturerAudit(selectedAudit);
                    }
                }
            }
        });

        // Add table to a scroll pane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void lancerAudit(Audit audit, int row) {
        // Update the audit status to "lance"
        audit.setStatus("lance");

        // Update the database
        AuditController.updateAudit(
                audit.getId(),
                audit.getDateDebut(),
                audit.getDateFin(),
                audit.getIntitule(),
                audit.getStatus(),
                audit.getSystemeExigenceId(),
                audit.getType(),
                audit.getAuditeurId()
        );

        // Update the table
        tableModel.setValueAt("lance", row, 3); // Assuming status is in column 3
    }

    private void cloturerAudit(Audit audit) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to close the audit: " + audit.getIntitule() + "?",
                "Confirm Closure",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Update status to "cloture"
            audit.setStatus("cloture");

            // Update the database
            AuditController.updateAudit(
                    audit.getId(),
                    audit.getDateDebut(),
                    audit.getDateFin(),
                    audit.getIntitule(),
                    "cloture",
                    audit.getSystemeExigenceId(),
                    audit.getType(),
                    audit.getAuditeurId()
            );

            // Generate audit report file
            generateAuditReport(audit);

            // Update the table
            int row = audits.indexOf(audit);
            tableModel.setValueAt("cloture", row, 3); // Assuming status is in column 3
        }
    }

    private void generateAuditReport(Audit audit) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "audit_report_" + audit.getId() + "_" + audit.getIntitule().replaceAll("\\s+", "_") + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("=== AUDIT REPORT ===\n\n");
            writer.write("Audit ID: " + audit.getId() + "\n");
            writer.write("Intitule: " + audit.getIntitule() + "\n");
            writer.write("Date Debut: " + audit.getDateDebut().format(formatter) + "\n");
            writer.write("Date Fin: " + audit.getDateFin().format(formatter) + "\n");
            writer.write("Status: " + audit.getStatus() + "\n");
            writer.write("Systeme Exigence ID: " + audit.getSystemeExigenceId() + "\n");
            writer.write("Type: " + audit.getType() + "\n");
            writer.write("Auditeur ID: " + audit.getAuditeurId() + "\n");
            writer.write("\nReport generated on: " + LocalDate.now().format(formatter));

            JOptionPane.showMessageDialog(
                    this,
                    "Audit report has been generated: " + fileName,
                    "Report Generated",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error generating audit report: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
