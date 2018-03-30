package org.coursework.frontend.face.Role;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.coursework.frontend.base.Role.BaseRole;

public class RolePanel extends JPanel implements BaseRole {

    JTextField roleNameField = new JTextField();
    JLabel errorLabel = new JLabel();
    
    public RolePanel(){
        init();
    }
    
    @Override
    public String getRoleName() {
        return roleNameField.getText();
    }
    
    private void init(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        add(new JLabel("Create Role", SwingConstants.CENTER), c);
        c.gridwidth = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        add(new JLabel("Role"), c);
        c.gridx = 1;
        c.weightx = 1.0;
        add(roleNameField, c);
    }
    
}
