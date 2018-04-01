package org.coursework.frontend.face.people;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.coursework.Main;
import org.coursework.backend.person.student.Student;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.menu.MenuPanel;

public class ViewStudentsPanel extends JPanel {

    private class OnCancelButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new MenuPanel());
            frame.repaint();
            frame.revalidate();
        }

    }

    List<Student> students = new ArrayList<>();

    public ViewStudentsPanel() {
        this(Main.getPeople(Student.class));
    }

    public ViewStudentsPanel(Student... students) {
        this(Arrays.asList(students));
    }

    public ViewStudentsPanel(Collection<Student> students) {
        this.students.addAll(students);
        init();
    }

    public Set<Student> getStudents() {
        return new HashSet<>(students);
    }

    private void init() {
        JPanel studentsPanel = new JPanel();
        studentsPanel.setLayout(new FlowLayout());
        students.forEach(s -> studentsPanel.add(new JLabel(s.getName())));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new OnCancelButton());
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(studentsPanel, c);
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        add(cancelButton, c);
    }

}
