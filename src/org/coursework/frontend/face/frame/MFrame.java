package org.coursework.frontend.face.frame;

import java.awt.Container;

import javax.swing.JFrame;

public class MFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public MFrame() {
        this("Connection", null);
    }

    public MFrame(String title, Container pane) {
        super(title);
        if (pane != null) {
            this.setContentPane(pane);
        }
        init();
    }

    private void init() {
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
