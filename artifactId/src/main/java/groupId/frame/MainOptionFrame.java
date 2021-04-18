package groupId.frame;

import java.awt.*;

import javax.swing.*;

import groupId.*;

public class MainOptionFrame extends CustomFrame{

    public JPanel mainPanel() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);
        var option = JsonController.readMainOption();

        info.setBottomInset(20);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.label("기본 설정", SwingConstants.CENTER));

        info.setAxis(Direction.toNextLine);
        info.setBottomInset(0);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);
        info.insertItem(FrameItems.label("기본 경로", SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 10);
        info.insertItem(FrameItems.textField(option.getMainPath(), textinputListener((value) -> {
            option.setMainPath(value);
            JsonController.saveMainOption(option);
        })));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);    
        info.insertItem(FrameItems.label("로그 헤더 prefix", SwingConstants.RIGHT));
        
        info.setBottomInset(20);
        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 10);
        info.insertItem(FrameItems.textField(option.getLogHeader(), textinputListener((value) -> {
            option.setLogHeader(value);
            JsonController.saveMainOption(option);
        })));

        info.setBottomInset(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.button("설정 내보내기"));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 2, 1, 1);
        info.insertItem(FrameItems.button("설정 가져오기"));

        info.setWeightY(1);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.NONE, 1, 1, 1);
        info.insertItem(FrameItems.label(""));

        return info.getPanel();
    }

    
}
