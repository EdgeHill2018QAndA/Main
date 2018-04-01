package org.coursework.frontend.face.groups;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.coursework.backend.group.Group;
import org.coursework.frontend.base.groups.BaseGroup;

public class GroupPanel extends JPanel implements BaseGroup {

    Group group;
    int id;

    private static final long serialVersionUID = 1L;

    public GroupPanel(int id) {
        this(id, null);
    }

    public GroupPanel(int id, Group group) {
        this.group = group;
        this.id = id;
        init();
    }

    @Override
    public Group getGroup() {
        return group;
    }

    private void init() {
        System.out.println("Creating Group Panel");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        add(new JLabel("Group: " + (1 + id), SwingConstants.CENTER), c);
        System.out.println("Added Group JLabel");
        group.getStudents().forEach((student) -> {
            c.gridy = +1;
            add(new JLabel(student.getName()), c);
            System.out.println("Added student: " + student.getName());
        });
    }

}
