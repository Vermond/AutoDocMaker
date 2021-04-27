package frame.view;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import frame.base.CustomFrame;
import frame.base.FrameItems;
import data.FileData;
import data.FileType;
import data.Strings;
import controller.FileDataController;

public class FileSettingFrame extends CustomFrame{
    
    private int selectedIndex = -1;
    private ArrayList<String> items = new ArrayList<String>();
    private int dropdownIndex = 1;
    private DefaultListModel<String> dataNameList;

    //Do not modify it directly
    private FileDataController controller = new FileDataController();    

    private TablePanelInfo leftPanelInfo;
    private TablePanelInfo rightPanelInfo;

    public FileSettingFrame() {
        dataNameList = controller.getAllDataName();
        controller.addPropertyChangeListener(new FileSettingChangeListener());

        // TODO : change to nice way if available
        items.add(Strings.fileTypeFixed);
        items.add(Strings.fileTypeAuto);
    }

    public JPanel mainPanel() {
        var insets = new Insets(10, 10, 10, 10);
        var info = new TablePanelInfo(insets);

        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.fileListTitle, SwingConstants.CENTER));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.button(Strings.exportAllButtonLabel, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.button(Strings.writeOutAllButtonLabel, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));

        info.setWeightY(1);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        info.insertItem(leftPanel());

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 3, 1, 3);
        info.insertItem(rightPanel());

        return info.getPanel();
    }

    JPanel leftPanel() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);
        leftPanelInfo = info;
        info.getPanel().setPreferredSize(new Dimension(300, 1000));
        
        addLeftPanelItems(info);

        return info.getPanel();
    }

    void addLeftPanelItems(TablePanelInfo info) {
        var list = new JList<String>(dataNameList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        if (selectedIndex >= 0) { list.setSelectedIndex(selectedIndex); }

        list.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedIndex = e.getFirstIndex();
                controller.setCurrentDataIndex(selectedIndex);
            }            
        });

        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setPreferredSize(new Dimension(250, 80));
        listScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        info.setWeightY(1);
        info.setBottomInset(20);
        info.setConstraints(GridBagConstraints.BOTH, 3, 1, 1);
        info.insertItem(listScroll);

        info.setWeightY(0);
        info.setBottomInset(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.button(Strings.addButtonLabel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Strings.defaultItemName;

                while(dataNameList.contains(name)) {
                    name = name + "_1";
                }

                controller.createData(name);
            }
        }));
        info.setAxis(Direction.toRight);

        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.button(Strings.deleteButtonLabel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removeCurrentData();        
            }
        }));
        info.setAxis(Direction.toRight);

        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.button(Strings.importButtonLabel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : file select dialog needed
                //controller.importData();
            }
        }));
    }

    JPanel rightPanel() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);
        rightPanelInfo = info;

        addRightPanelItems(info);

        return info.getPanel();
    }

    void addRightPanelItems(TablePanelInfo info) {
        rightPanelInfo.setWeightY(0);
        rightPanelInfo.setBottomInset(20);
        rightPanelInfo.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        rightPanelInfo.insertItem(commonInputArea());

        rightPanelInfo.setWeightY(1);
        rightPanelInfo.setBottomInset(0);
        rightPanelInfo.setAxis(Direction.toNextLine);
        rightPanelInfo.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        rightPanelInfo.insertItem(rightChangeArea());
    }

    JPanel rightChangeArea() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);

        info.setWeightY(1);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        switch (dropdownIndex) {
            case FileData.FILE_FIXED:
            info.insertItem(fixedFileInputArea());
            break;
            case FileData.FILE_GENERATE:
            info.insertItem(unFixedFileInputArea());
            break;
        }

        return info.getPanel();
    }

    JPanel commonInputArea() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);

        info.setConstraints(GridBagConstraints.BOTH, 2, 1, 1);
        info.insertItem(FrameItems.button(Strings.exportButtonLabel));

        info.setBottomInset(20);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.BOTH, 2, 1, 1);
        info.insertItem(FrameItems.button(Strings.writeButtonLabel));

        info.setBottomInset(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.fileNameLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 9);
        info.insertItem(FrameItems.textField(controller.getFileName(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.filePathLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 9);
        info.insertItem(FrameItems.textField(controller.getFilePath(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.fileTypeLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 9);
        info.insertItem(FrameItems.comboBox(items.toArray(new String[items.size()]), dropdownIndex, new TypeListItemListener()));

        return info.getPanel();
    }

    JPanel fixedFileInputArea() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);

        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        var label = FrameItems.label(Strings.contentLabel, SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(100, 80));
        info.insertItem(label);

        info.setWeightY(1);
        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 5);
        var field = FrameItems.multiTextField(controller.getContent(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        }));
        field.setPreferredSize(new Dimension(1000, 80));
        info.insertItem(field);

        return info.getPanel();
    }

    JPanel unFixedFileInputArea() {
        var insets = new Insets(0, 10, 0, 10);
        var info = new TablePanelInfo(insets);

        info.setWeightY(1);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        var label = FrameItems.label(Strings.filterRuleLabel, SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(50, 50));
        info.insertItem(label);

        info.setBottomInset(20);
        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 2, 1, 8);
        var field = FrameItems.multiTextField(controller.getFilter(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        }));
        field.setPreferredSize(new Dimension(1000, 80));
        info.insertItem(field);

        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.filterOutputLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 2, 1, 8);
        info.insertItem(FrameItems.multiTextField(controller.getFilterOutput(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));

        info.setWeightY(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.specialDescLabel, SwingConstants.RIGHT));

        info.setWeightY(1);
        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 1, 3, 4);
        info.insertItem(FrameItems.multiTextField(controller.getSpecialOption(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 1, 3, 4);
        info.insertItem(FrameItems.multiTextField(controller.getSpecialContent(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));

        info.setBottomInset(0);
        info.setWeightY(0);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.HORIZONTAL, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.specialDescHintLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toNextLine);

        info.setWeightY(1);
        info.setBottomInset(20);
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.prefixHeaderLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 2, 1, 8);
        info.insertItem(FrameItems.multiTextField(controller.getHeader(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));
        info.setAxis(Direction.toNextLine);
        info.setConstraints(GridBagConstraints.BOTH, 1, 1, 1);
        info.insertItem(FrameItems.label(Strings.prefixEndLabel, SwingConstants.RIGHT));

        info.setAxis(Direction.toRight);
        info.setConstraints(GridBagConstraints.BOTH, 2, 1, 8);
        info.insertItem(FrameItems.multiTextField(controller.getFooter(), textinputListener((value) -> {
        }),
        focusListener(null, () -> {
        })));

        return info.getPanel();
    }

    void reDrawLeftArea() {
        leftPanelInfo.getPanel().setVisible(false);

        leftPanelInfo.removeAllChild();

        addLeftPanelItems(leftPanelInfo);

        leftPanelInfo.getPanel().invalidate();
        leftPanelInfo.getPanel().setVisible(true);
    }

    void reDrawRightArea() {
        rightPanelInfo.getPanel().setVisible(false);

        rightPanelInfo.removeAllChild();

        addRightPanelItems(rightPanelInfo);

        rightPanelInfo.getPanel().invalidate();
        rightPanelInfo.getPanel().setVisible(true);
    }

    class TypeListItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var item = e.getItem();
                dropdownIndex = items.indexOf(item);
                controller.setFileType(FileType.fromInt(selectedIndex));
            }            
        }
    }

    class FileSettingChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch(evt.getPropertyName()) {
                case Strings.createDataVar:
                case Strings.deleteDataVar:
                case Strings.importDataVar:
                dataNameList = controller.getAllDataName();
                selectedIndex = controller.getCurrentDataIndex();
                reDrawLeftArea();
                reDrawRightArea();
                break;
                case Strings.dataIndexVar:
                reDrawLeftArea();
                reDrawRightArea();
                break;
                case Strings.fileTypeVar:
                reDrawRightArea();
                break;
                case Strings.fileNameVar:
                case Strings.filePathVar:
                case Strings.contentVar:
                case Strings.filterRuleVar:
                case Strings.filterOutputVar:
                case Strings.specialDescVar:
                case Strings.prefixHeaderVar:
                case Strings.prefixEndVar:
                //Do nothing
                break;
                default:
                break;
            }            
        }
    }
}
