package org.coursework.frontend.face.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.coursework.Main;
import org.coursework.backend.person.Permission;
import org.coursework.frontend.face.Role.RolePanel;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.groups.GroupsPanel;
import org.coursework.frontend.face.people.StudentPanel;

public class MenuPanel extends JPanel {

    private class OnStudentButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new StudentPanel());
            frame.repaint();
            frame.revalidate();
        }

    }
    
    private class OnRoleButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new RolePanel());
            frame.repaint();
            frame.revalidate();
        }
        
    }

    private class OnGroupsButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new GroupsPanel());
            frame.repaint();
            frame.revalidate();
        }

    }

    private static final long serialVersionUID = 1L;

    public MenuPanel() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(createNewStudentButton(), c);
        c.gridy = 1;
        add(createNewRoleButton(), c);
        c.gridy = 2;
        add(viewGroups(), c);
    }

    private JButton createNewStudentButton() {
        JButton button = new JButton("New Student");
        button.addActionListener(new OnStudentButton());
        return button;
    }

    private JButton createNewRoleButton() {
        JButton button = new JButton("New Role");
        button.addActionListener(new OnRoleButton());
        if (!Main.getLoggedInAs().isPresent()) {
            System.out.println("Login is not present");
            button.setEnabled(false);
        } else if (Main.getLoggedInAs().get().getPermission().equals(Permission.STUDENT)) {
            System.out.println("Login has permission of " + Main.getLoggedInAs().get().getPermission().name());
            button.setEnabled(false);
        }
        return button;
    }

    private JButton viewGroups() {
        JButton button = new JButton("View Groups");
        button.addActionListener(new OnGroupsButton());
        if (Main.getGroups().size() != 0) {
            if (!Main.getLoggedInAs().isPresent()) {
                button.setEnabled(false);
            } else if (!Main.getLoggedInAs().get().getPermission().equals(Permission.STUDENT)) {
                button.setEnabled(false);
            }
        }
        return button;
    }

}
