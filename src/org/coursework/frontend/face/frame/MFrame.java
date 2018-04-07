package org.coursework.frontend.face.frame;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Optional;

import javax.swing.JFrame;
import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;

public class MFrame extends JFrame {
    
    private class OnWindowClose implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {
            Optional<CoreDatabaseLink> opLink = Main.getDatabaseLink();
            if(!opLink.isPresent()){
                return;
            }
            CoreDatabaseLink link = opLink.get();
            link.saveData();
        }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
        
    }

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
        addWindowListener(new MFrame.OnWindowClose());
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public int setProgress(int percent){
        //java 9 only
        return percent;
    }
    
    public int setProgress(int value, int max){
        int percent = (int)((value * 100.0f)/max);
        return setProgress(percent);
    }
    
    public int setProgress(int min, int value, int max){
        return setProgress((value-min), (max-min));
    }

}
