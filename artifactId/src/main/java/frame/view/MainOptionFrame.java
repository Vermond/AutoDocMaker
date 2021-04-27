package frame.view;

import java.awt.*;

import javax.swing.*;

import controller.JsonController;
import data.Strings;
import frame.base.CustomFrame;
import frame.base.FrameItems;


public class MainOptionFrame extends CustomFrame{

    public JPanel mainPanel() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);
        var option = JsonController.readMainOption(Strings.defaultMainOptionPath);

        info.setBottomInset(20);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.label(Strings.defaultSettingTitle, SwingConstants.CENTER));

        info.setAxis(Direction.toNextLine);
        info.setBottomInset(0);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.defaultPathLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 10);        
        info.insertItem(FrameItems.textField(option.getMainPath(), textinputListener((value) -> {
            option.setMainPath(value);
            JsonController.writeMainOption(option, Strings.defaultMainOptionPath);
        })));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);    
        info.insertItem(FrameItems.label(Strings.defaultPrefixLabel, SwingConstants.RIGHT));
        
        info.setBottomInset(20);
        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 10);
        info.insertItem(FrameItems.textField(option.getLogHeader(), textinputListener((value) -> {
            option.setLogHeader(value);
            JsonController.writeMainOption(option, Strings.defaultMainOptionPath);
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

    
}
