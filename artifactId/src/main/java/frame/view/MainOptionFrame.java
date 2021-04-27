package frame.view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import controller.MainOptionController;
import data.Strings;
import frame.base.CustomFrame;
import frame.base.FrameItems;

public class MainOptionFrame extends CustomFrame{

    private MainOptionController controller = new MainOptionController();

    private String _mainPath = "";
    private String _logHeader = "";
    private JFrame parentFrame;

    public MainOptionFrame(JFrame parent) {
        parentFrame = parent;
        controller.addPropertyChangeListener(new OptionChangeListener());
    }

    public JPanel mainPanel() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);

        info.setBottomInset(20);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.label(Strings.defaultSettingTitle, SwingConstants.CENTER));

        info.setAxis(Direction.toNextLine);
        info.setBottomInset(0);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.defaultPathLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 10);        
        info.insertItem(FrameItems.textField(controller.getMainPath(), textinputListener((value) -> {
            _mainPath = value;
        }),
        focusListener(()->{}, () -> {
            controller.setMainPath(_mainPath);
        })));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);    
        info.insertItem(FrameItems.label(Strings.defaultLogPrefixLabel, SwingConstants.RIGHT));
        
        info.setBottomInset(20);
        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 10);
        info.insertItem(FrameItems.textField(controller.getLogHeader(), textinputListener((value) -> {
            _logHeader = value;
        }),
        focusListener(()->{}, () -> {
            controller.setLogHeader(_logHeader);
        })));

        info.setBottomInset(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.button(Strings.exportOptionButtonLabel, () -> {
            //TODO : file dialog sample code-move it
            FileDialog fd = new FileDialog(parentFrame, "Choose a file", FileDialog.LOAD);
            fd.setDirectory("C:\\");
            fd.setFile("*.xml");
            fd.setVisible(true);
            String filename = fd.getDirectory();
            if (filename == null)
                System.out.println("You cancelled the choice");
            else
                System.out.println("You chose " + filename);

        }));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.button(Strings.importOptionButtonLabel, () -> {
            //TODO : directory dialog sample code-move it
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("choosertitle");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
        
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
              System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
              System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            } else {
              System.out.println("No Selection ");
            }

        }));
        info.setWeightY(1);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);
        info.insertItem(FrameItems.label(""));

        return info.getPanel();
    }

    class OptionChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch(evt.getPropertyName()) {
                case Strings.defaultPathVar:
                case Strings.defaultLogPrefixVar:
                controller.saveOption(Strings.defaultMainOptionPath);
                break;
                default:
                break;
            }            
        }
    }
    
}
