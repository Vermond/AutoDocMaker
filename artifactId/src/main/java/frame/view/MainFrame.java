package frame.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 4031505629974192240L;

    private static final String version = "1.0.0";

    JTabbedPane pane = new JTabbedPane();

    
    public MainFrame(int width, int height) {
        var main = new MainOptionFrame();
        pane.addTab("메인 설정", main.mainPanel());

        var fileSetting = new FileSettingFrame();
        pane.addTab("파일 설정", fileSetting.mainPanel());

        this.add(pane);

        setSize(width, height);
        setLocation(0,0);
        setTitle("주석 문서화 Ver. " + version);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
