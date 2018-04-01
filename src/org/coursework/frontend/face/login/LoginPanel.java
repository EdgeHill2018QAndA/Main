package org.coursework.frontend.face.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.coursework.Main;
import org.coursework.backend.person.Person;
import org.coursework.frontend.base.login.BaseLogin;
import org.coursework.frontend.face.connection.ConnectionPanel;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.menu.MenuPanel;

public class LoginPanel extends JPanel implements BaseLogin {

    private class OnChangeConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new ConnectionPanel());
            frame.repaint();
            frame.revalidate();
        }

    }

    private class OnLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Optional<Person> opPerson = LoginPanel.this.checkAuthorization();
            if (!opPerson.isPresent()) {
                LoginPanel.this.errorLabel.setText("Failed to find account with those details");
                return;
            }
            LoginPanel.this.forceAuthorization(opPerson.get());
        }

    }

    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JLabel errorLabel = new JLabel();
    
	private static final long serialVersionUID = 1L;

    public LoginPanel() {
        init();
    }

    @Override
    public void nextPage() {
        MFrame frame = Main.getFrame();
        frame.setContentPane(new MenuPanel());
        frame.repaint();
        frame.revalidate();
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
        JButton changeConnectionButton = new JButton("Change Connection");
        JButton loginButton = new JButton("Login");
        JPanel buttonPanel = new JPanel();
        changeConnectionButton.addActionListener(new OnChangeConnectionListener());
        loginButton.addActionListener(new OnLoginListener());
        buttonPanel.add(changeConnectionButton);
        buttonPanel.add(loginButton);
        buttonPanel.setLayout(new GridLayout(1, 2));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Login", SwingConstants.CENTER), c);
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.0;
        add(new JLabel("First name"), c);
        c.gridx = 1;
        c.weightx = 1.0;
        add(firstNameField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        add(new JLabel("Last name"), c);
        c.gridx = 1;
        add(lastNameField, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 1.0;
        c.gridwidth = 2;
        add(errorLabel, c);
        c.weighty = 0.0;
        c.gridy = 4;

        add(buttonPanel, c);
    }

}
