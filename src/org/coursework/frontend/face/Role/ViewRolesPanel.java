package org.coursework.frontend.face.Role;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.coursework.Main;
import org.coursework.backend.roles.Role;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.menu.MenuPanel;

public class ViewRolesPanel extends JPanel {

    private class OnCancelButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MFrame frame = Main.getFrame();
            frame.setContentPane(new MenuPanel());
            frame.repaint();
            frame.revalidate();
        }

    }

    List<Role> roles = new ArrayList<>();
    
	private static final long serialVersionUID = 1L;

    public ViewRolesPanel() {
        this(Main.getRoles());
    }

    public ViewRolesPanel(Role... roles) {
        this(Arrays.asList(roles));
    }

    public ViewRolesPanel(Collection<Role> roles) {
        this.roles.addAll(roles);
        init();
    }

    private void init() {
        JPanel rolesPanel = new JPanel();
        JButton cancelPanel = new JButton("Cancel");
        cancelPanel.addActionListener(new OnCancelButton());
        rolesPanel.setLayout(new FlowLayout());
        this.roles.forEach(r -> rolesPanel.add(new JLabel(r.getDisplayName())));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(rolesPanel, c);
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        add(cancelPanel, c);
    }

}
