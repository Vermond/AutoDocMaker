package frame.base;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import listener.InputFocusListener;
import listener.InputFocusListener.FocusAction;

public class CustomFrame {
    
    public enum Direction { 
        toRight,
        toNextLine,
    }

    @FunctionalInterface
    public interface InputTextListener {
        public void action(String value);    
    }

    @FunctionalInterface
    interface Insert {
        void insert(Component item) ;
    }

    @FunctionalInterface
    interface AxisFinc {
        void SetAxis(Direction dir, Axis axis);
    }

    class Axis {
        int x;
        int y;

        Axis() {
            x = 0;
            y = 0;
        }
    }

    public class TablePanelInfo {
        private JPanel panel;
        private GridBagLayout layout;
        private GridBagConstraints con;
        private Axis axis;
        private Insert insert;
        private AxisFinc axisController;

        public TablePanelInfo(Insets _inset) {
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            layout = new GridBagLayout();
            con = new GridBagConstraints();
            axis = new Axis();

            insert = (item) -> insertItemBase(panel, layout, con, item);
            axisController = (dir, _axis) -> setAxisBase(dir, _axis);

            con.anchor = GridBagConstraints.NORTH;
            con.gridwidth = GridBagConstraints.REMAINDER;
            con.insets = _inset;

            panel.setLayout(layout);
        }

        public JPanel getPanel() { return panel; }
        GridBagConstraints getConst() { return con; }
        Axis getAxis() { return axis; }

        public void setAxis(Direction dir) { axisController.SetAxis(dir, axis); }
        public void setWeightY(int value) { con.weighty = value; }
        public void setBottomInset(int value) { con.insets.bottom = value; }
        public void setConstraints(int fill, int width, int height, double weightX) { setConstraintsBase(con, fill, axis, width, height, weightX); }
        
        public void insertItem(Component item) { insert.insert(item); }
        public void removeAllChild() { 
            panel.removeAll();
            con.gridx = 0;
            con.gridy = 0;
            axis.x = 0;
            axis.y = 0;
        }
    }

    void setConstraintsBase(GridBagConstraints con, int fill, Axis axis, int width, int height, double weightX) {
        con.fill = fill;

        con.gridx = axis.x;
        con.gridy = axis.y;
        con.gridwidth = width;
        con.gridheight = height;
        con.weightx = weightX;
    }

    void insertItemBase(JPanel targetPanel, GridBagLayout targetLayout, GridBagConstraints con, Component item) {
        targetLayout.setConstraints(item, con);
        targetPanel.add(item);
    }

    void setAxisBase(Direction dir, Axis axis) {
        switch (dir) {
            case toRight:
            axis.x+=1;
            break;
            case toNextLine:
            axis.x = 0;
            axis.y +=1;
            break;
        }
    }

    protected InputFocusListener focusListener(FocusAction onGained, FocusAction onLost) {
        return new InputFocusListener(onGained, onLost);
    }

    protected DocumentListener textinputListener(InputTextListener function) {
        return new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                var doc = e.getDocument();
                try {
                    function.action(doc.getText(0, doc.getLength()));
                } catch (BadLocationException e1) {
                    System.out.println("BadLocationException : " + e1.getMessage());
                }                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                var doc = e.getDocument();
                try {
                    function.action(doc.getText(0, doc.getLength()));
                } catch (BadLocationException e1) {
                    System.out.println("BadLocationException : " + e1.getMessage());
                } 
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                var doc = e.getDocument();
                try {
                    function.action(doc.getText(0, doc.getLength()));
                } catch (BadLocationException e1) {
                    System.out.println("BadLocationException : " + e1.getMessage());
                } 
            }
        };
    }
}
