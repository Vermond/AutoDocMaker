package frame.view;

import javax.swing.*;

import data.Strings;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 4031505629974192240L;


    JTabbedPane pane = new JTabbedPane();

    
    public MainFrame(int width, int height) {
        var main = new MainOptionFrame();
        pane.addTab(Strings.mainTapLabel, main.mainPanel());

        var fileSetting = new FileSettingFrame();
        pane.addTab(Strings.fileTapLabel, fileSetting.mainPanel());

        this.add(pane);

        setSize(width, height);
        setLocation(0,0);
        setTitle(Strings.appTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
