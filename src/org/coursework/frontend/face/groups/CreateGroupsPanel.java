package org.coursework.frontend.face.groups;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import org.coursework.Main;
import org.coursework.backend.group.Group;
import org.coursework.backend.group.GroupSort;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.roles.Role;
import org.coursework.frontend.face.frame.MFrame;

public class CreateGroupsPanel extends JPanel {

    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            previousScreen();
        }

    }

    private class SortListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Set<Group> groups = new GroupSort().sortGroups(list.getSelectedValuesList());
            System.out.println("Sorted groups: " + groups.size());
            Main.setGroups(groups);
            previousScreen();
        }

    }

    JList<Role> list;

    private static final long serialVersionUID = 1L;

    public CreateGroupsPanel() {
        init();
    }

    public void previousScreen() {
        MFrame frame = Main.getFrame();
        frame.setContentPane(new GroupsPanel());
        frame.repaint();
        frame.revalidate();
    }

    private void init() {
        setLayout(new GridBagLayout());
        DefaultListModel<Role> model = new DefaultListModel<Role>();
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Main.getRoles().stream().forEach(r -> {
            model.addElement(r);
        });
        JButton sortButton = new JButton("Sort");
        JButton cancelButton = new JButton("Cancel");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        add(list, c);
        c.gridy = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(cancelButton, c);
        c.gridx = 1;
        add(sortButton, c);
        cancelButton.addActionListener(new CancelListener());
        sortButton.addActionListener(new SortListener());
    }

}
