package org.coursework.frontend.face.groups;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.coursework.Main;
import org.coursework.backend.group.Group;
import org.coursework.frontend.base.groups.BaseGroups;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.menu.MenuPanel;

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
    
    private class OnCancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new MenuPanel());
            frame.repaint();
            frame.revalidate();
        }
        
    }

    List<Group> groups = new ArrayList<>();

    public GroupsPanel(){
        this(Main.getGroups());
    }
    
    public GroupsPanel(Group... groups){
        this(Arrays.asList(groups));
    }
    
    public GroupsPanel(Collection<Group> groups) {
        this.groups.addAll(groups);
        System.out.println("GroupsPanel: Groups: " + groups.size());
        init();
    }

    @Override
    public Set<Group> getGroups() {
        return new HashSet<>(groups);
    }

    private void init() {
        JButton cancelButton = new JButton("Cancel Button");
        JButton createGroups = new JButton("Create Groups");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (int A = 0; A < getGroups().size(); A++) {
            System.out.println("Found group");
            Group group = (Group) this.groups.get(A);
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
        c.gridy = 2;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(cancelButton, c);
        createGroups.addActionListener(new SortGroup());
        cancelButton.addActionListener(new OnCancelListener());
    }

}
