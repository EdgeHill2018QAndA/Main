package org.coursework.frontend.face.Role;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.coursework.Main;
import org.coursework.backend.roles.Role;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.menu.MenuPanel;
import org.coursework.frontend.base.Role.CreateBaseRole;

public class CreateRolePanel extends JPanel implements CreateBaseRole {

    private class OnCancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            previousPage();
        }

    }

    private class OnDeleteSelectedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JList list = CreateRolePanel.this.currentRoles;
            DefaultListModel<Role> model = (DefaultListModel<Role>) list.getModel();
            list.getSelectedValuesList().forEach(v -> {
                Role role = (Role) v;
                model.removeElement(role);
                Main.deregister(role);
            });
        }

    }

    private class OnCreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Role role = CreateRolePanel.this.createRole();
                JLabel label = CreateRolePanel.this.errorLabel;
                if (!CreateRolePanel.this.registerRole(role)) {
                    label.setText("Role has already been created");
                    return;
                }
                label.setText("Created " + role.getDisplayName());
                ((DefaultListModel<Role>) CreateRolePanel.this.currentRoles.getModel()).addElement(role);
            } catch (SQLException e2) {
                JOptionPane.showMessageDialog(null, e2.getMessage(), "Error: Could not create role", JOptionPane.ERROR_MESSAGE);
                e2.printStackTrace();
            }
        }
    }

    JTextField roleNameField = new JTextField();
    JLabel errorLabel = new JLabel();
    JList currentRoles;
    JButton deleteSelectedRoles = new JButton("Delete selected roles");

    public CreateRolePanel() {
        init();
    }

    public void previousPage() {
        MFrame frame = Main.getFrame();
        frame.setContentPane(new MenuPanel());
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public String getRoleName() {
        return roleNameField.getText();
    }

    private void init() {
        JButton createButton = new JButton("Create Role");
        JButton cancelButton = new JButton("Cancel");
        deleteSelectedRoles.addActionListener(new OnDeleteSelectedListener());
        createButton.addActionListener(new OnCreateListener());
        cancelButton.addActionListener(new OnCancelListener());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(cancelButton);
        buttonPanel.add(createButton);
        DefaultListModel<Role> model = new DefaultListModel<>();
        this.currentRoles = new JList(model);
        this.currentRoles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Main.getRoles().forEach(r -> {
            model.addElement(r);
        });
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        add(new JLabel("Create Role", SwingConstants.CENTER), c);
        c.gridwidth = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        add(new JLabel("Role"), c);
        c.gridx = 1;
        c.weightx = 1.0;
        add(roleNameField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weighty = 1.0;
        add(currentRoles, c);
        c.gridy = 3;
        c.weighty = 0.0;
        add(errorLabel, c);
        c.gridy = 4;
        add(deleteSelectedRoles, c);
        c.gridy = 5;
        add(buttonPanel, c);
    }

}
