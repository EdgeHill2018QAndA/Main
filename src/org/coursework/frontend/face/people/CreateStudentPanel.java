package org.coursework.frontend.face.people;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.coursework.Main;
import org.coursework.backend.person.Permission;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.menu.MenuPanel;
import org.coursework.frontend.base.people.CreateBaseStudent;

public class CreateStudentPanel extends JPanel implements CreateBaseStudent {

    private class OnCancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateStudentPanel.this.menuScreen();
        }

    }

    private class OnSelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(CreateStudentPanel.this.getModifyStudent().isPresent()){
                CreateStudentPanel.this.updateOption();
                return;
            }
            try {
                StudentOption option = CreateStudentPanel.this.createOption();
                Main.register(option);
                Main.register(option.getStudent());
                CreateStudentPanel.this.menuScreen();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    private class OnItemSelectListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            JComboBox<?> source = (JComboBox<?>) event.getSource();
            Role role = (Role) event.getItem();
            if (CreateStudentPanel.this.chosenRoles.stream().filter(c -> !c.equals(source)).anyMatch(c -> c.getSelectedItem().equals(role))) {
                source.setBackground(Color.RED);
                return;
            }
            source.setBackground(null);
            CreateStudentPanel.this.updateBoxes();
        }

    }

    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JButton cancelButton = new JButton("Cancel");
    JButton acceptButton = new JButton("Create");
    Collection<Role> allRoles;
    JPanel comboPanel = new JPanel();
    List<JComboBox<Role>> chosenRoles = new ArrayList<>();
    Student modify;

    private static final long serialVersionUID = 1L;

    public CreateStudentPanel() {
        this(null, Main.getRoles());
    }
    
    public CreateStudentPanel(Student student){
        this(student, Main.getRoles());
    }

    public CreateStudentPanel(Role... roles) {
        this(null, Arrays.asList(roles));
    }
    
    public CreateStudentPanel(Student student, Role... roles){
        this(student, Arrays.asList(roles));
    }

    public CreateStudentPanel(Student modify, Collection<Role> roles) {
        this.allRoles = roles;
        this.modify = modify;
        init();
    }

    public void menuScreen() {
        MFrame frame = Main.getFrame();
        frame.setContentPane(new MenuPanel());
        frame.repaint();
        frame.revalidate();
    }

    public void updateBoxes() {
        List<JComboBox<Role>> remove = new ArrayList<>();
        for (int A = 0; A < (chosenRoles.size() - 1); A++) {
            JComboBox<Role> role = chosenRoles.get(A);
            if (role.getSelectedItem().toString().equals("")) {
                remove.add(role);
            }
        }
        chosenRoles.removeAll(remove);
        List<Role> missing = allRoles.stream().filter(r -> !chosenRoles.stream().anyMatch(c -> c.getSelectedItem().equals(r))).collect(Collectors.toList());
        chosenRoles.stream().forEach(c -> {
            missing.stream().forEach(r -> {
                for (int A = 0; A < c.getItemCount(); A++) {
                    Role role = (Role) c.getItemAt(A);
                    if (r.equals(role)) {
                        return;
                    }
                }
                c.addItem(r);
            });
        });
        if (chosenRoles.isEmpty()) {
            chosenRoles.add(createRoleBox());
        } else if ((!chosenRoles.get(chosenRoles.size() - 1).getSelectedItem().toString().equals("")) && (chosenRoles.size() != allRoles.size())) {
            chosenRoles.add(createRoleBox());
        }
        comboPanel.removeAll();
        comboPanel.setLayout(new FlowLayout());
        for (int A = 0; A < chosenRoles.size(); A++) {
            JLabel label = new JLabel("Chosen Role " + (A + 1));
            JComboBox<Role> box = chosenRoles.get(A);
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2));
            panel.add(label);
            panel.add(box);
            comboPanel.add(panel);
        }
        comboPanel.repaint();
        comboPanel.revalidate();
    }

    @Override
    public Optional<Student> getModifyStudent(){
        return Optional.ofNullable(modify);
    }
    
    @Override
    public String getFirstName() {
        return firstNameField.getText();
    }

    @Override
    public String getLastName() {
        return lastNameField.getText();
    }

    @Override
    public List<String> getSelectedRoles() {
        List<String> roles = new ArrayList<>();
        chosenRoles.stream().filter(c -> !c.getSelectedItem().toString().equals("")).forEach(c -> roles.add(c.getSelectedItem().toString()));
        return roles;
    }

    @Override
    public Collection<Role> getChoosableRoles() {
        return allRoles;
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("First Name:"), c);
        c.gridx = 1;
        c.weightx = 1.0;
        add(firstNameField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        add(new JLabel("Last Name:"), c);
        c.gridx = 1;
        add(lastNameField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(comboPanel, c);
        c.gridy = 3;
        c.weighty = 0.0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        JPanel panel = new JPanel();
        add(panel, c);
        panel.setLayout(new GridLayout(1, 2));
        panel.add(cancelButton);
        panel.add(acceptButton);
        cancelButton.addActionListener(new OnCancelListener());
        acceptButton.addActionListener(new OnSelectListener());
        if (Main.getLoggedInAs().get().getPermission().equals(Permission.STUDENT)) {
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            firstNameField.setText(Main.getLoggedInAs().get().getFirstName());
            lastNameField.setText(Main.getLoggedInAs().get().getLastName());
        }
        if(modify != null){
            acceptButton.setText("Update");
            Optional<StudentOption> opOption = getModifyOption();
            if(opOption.isPresent()){
                StudentOption option = opOption.get();
                for(Role role : option.getRoles()){
                    JComboBox<Role> roleBox = createRoleBox();
                    roleBox.setSelectedItem(role);
                    chosenRoles.add(roleBox);
                }
            }
        }
        updateBoxes();
    }

    private JComboBox<Role> createRoleBox() {
        JComboBox<Role> first = new JComboBox<>();
        first.addItem(new Role(Integer.MAX_VALUE, ""));
        allRoles.stream().filter(r -> !chosenRoles.stream().anyMatch(c -> c.getSelectedItem().equals(r))).forEach(f -> first.addItem(f));
        first.addItemListener(new OnItemSelectListener());
        return first;
    }

}
