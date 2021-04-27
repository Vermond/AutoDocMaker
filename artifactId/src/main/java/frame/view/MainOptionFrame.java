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

    public MainOptionFrame() {
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
        focusListener(null, () -> {
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
        focusListener(null, () -> {
            controller.setLogHeader(_logHeader);
        })));

        info.setBottomInset(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.button(Strings.exportOptionButtonLabel));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.button(Strings.importOptionButtonLabel));

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
