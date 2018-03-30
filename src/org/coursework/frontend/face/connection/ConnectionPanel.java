package org.coursework.frontend.face.connection;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.coursework.Main;
import org.coursework.frontend.face.frame.MFrame;

import org.coursework.frontend.base.connection.BaseConnection;
import org.coursework.frontend.face.login.LoginPanel;

public class ConnectionPanel extends JPanel implements BaseConnection {

    private class OnAttemptConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                ConnectionPanel.this.attemptConnection();
            } catch (IOException e) {
                ConnectionPanel.this.errorLabel.setText("The required driver could not be loaded");
                e.printStackTrace();
                return;
            } catch (SQLException e) {
                ConnectionPanel.this.errorLabel.setText("Could not connect: " + e.getMessage());
                e.printStackTrace();
                return;
            }
            MFrame frame = Main.getFrame();
            frame.setContentPane(new LoginPanel());
            frame.repaint();
            frame.revalidate();

        }

    }

    JTextField urlField = new JTextField();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel errorLabel = new JLabel();
    JButton connectionButton = new JButton("Connect");

    private static final long serialVersionUID = 1L;

    public ConnectionPanel() {
        init();
    }

    @Override
    public String getURL() {
        return urlField.getText();
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("A connection to a database is required"), c);
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.0;
        add(new JLabel("Location:"), c);
        c.gridx = 1;
        c.weightx = 1.0;
        add(urlField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.0;
        add(new JLabel("Username:"), c);
        c.gridx = 1;
        add(usernameField, c);
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Password:"), c);
        c.gridx = 1;
        add(passwordField, c);
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 2;
        add(errorLabel, c);
        c.gridy = 5;
        c.weighty = 0.0;
        add(connectionButton, c);
        connectionButton.addActionListener(new OnAttemptConnection());
    }

}
