package org.coursework.frontend.face.groups;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.coursework.Main;
import org.coursework.backend.group.Group;
import org.coursework.frontend.base.groups.BaseGroups;
import org.coursework.frontend.face.frame.MFrame;

public class GroupsPanel extends JPanel implements BaseGroups {

    private class SortGroup implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new CreateGroupsPanel());
            frame.repaint();
            frame.revalidate();
        }

    }

    List<Group> group = new ArrayList<>();

    public GroupsPanel() {
        init();
    }

    @Override
    public Set<Group> getGroups() {
        return new HashSet<>(group);
    }

    private void init() {
        JButton createGroups = new JButton("Create Groups");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (int A = 0; A < group.size(); A++) {
            Group group = (Group) this.group;
            panel.add(new GroupPanel(A, group));
        }
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(createGroups, c);
        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(panel, c);
        createGroups.addActionListener(new SortGroup());
    }

}
