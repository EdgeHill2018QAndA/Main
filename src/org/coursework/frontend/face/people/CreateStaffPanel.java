package org.coursework.frontend.face.people;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.coursework.Main;
import org.coursework.frontend.base.people.CreateBaseStaff;
import org.coursework.frontend.face.connection.ConnectionPanel;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.login.LoginPanel;

public class CreateStaffPanel extends JPanel implements CreateBaseStaff {

    private class OnCancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new ConnectionPanel());
            frame.repaint();
            frame.revalidate();
        }

    }

    private class OnApplyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CreateStaffPanel.this.registerStaff();
                MFrame frame = Main.getFrame();
                frame.setContentPane(new LoginPanel());
                frame.repaint();
                frame.revalidate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error: Could not create account.", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }

    }

    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();

    public CreateStaffPanel() {
        init();
    }

    @Override
    public String getFirstName() {
        return firstNameField.getText();
    }

    @Override
    public String getLastName() {
        return lastNameField.getText();
    }

    private void init() {
        JButton cancelButton = new JButton("Cancel");
        JButton applyLoginButton = new JButton("Apply Login");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(cancelButton);
        buttonPanel.add(applyLoginButton);
        cancelButton.addActionListener(new OnCancelListener());
        applyLoginButton.addActionListener(new OnApplyListener());
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        add(new JLabel("Create master login"), c);
        c.gridwidth = 1;
        c.gridy = 1;
        add(new JLabel("First Name"), c);
        c.gridx = 1;
        c.weightx = 1.0;
        add(firstNameField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.0;
        add(new JLabel("Last name"), c);
        c.gridx = 1;
        add(lastNameField, c);
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        add(new JPanel(), c);
        c.gridy = 4;
        add(buttonPanel, c);
    }

}
