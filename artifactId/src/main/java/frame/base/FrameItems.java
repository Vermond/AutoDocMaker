package frame.base;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;

import listener.InputFocusListener;

public class FrameItems {

    @FunctionalInterface
    public interface ClickAction {
        public void action();    
    }

    public static JButton button(String title, ClickAction action) {
        var btn = new JButton(title);

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                action.action();                
            }
        });

        return btn;
    }

    public static JButton button(String title) {
        return new JButton(title);
    }

    public static JLabel label(String value) {
        return new JLabel(value); 
    }
    
    public static JLabel label(String value, int alignment) {
        var label = new JLabel(value);
        label.setHorizontalAlignment(alignment);
        return label;        
    }

    public static JTextField textField(String inputDefault, DocumentListener documentListener, InputFocusListener focusListener) {
        JTextField input = new JTextField(inputDefault);
        input.getDocument().addDocumentListener(documentListener);
        input.addFocusListener(focusListener);

        return input;
    }

    public static JScrollPane multiTextField(String inputDefault, DocumentListener documentListener, InputFocusListener focusListener) {
        JTextArea input = new JTextArea(inputDefault);
        input.setLineWrap(true);
        input.getDocument().addDocumentListener(documentListener);
        input.addFocusListener(focusListener);

        var scroll = new JScrollPane(input);

        return scroll;
    }
    
    public static JComboBox<String> comboBox(String[] items, int index, ItemListener event) {
        var dropdown = new JComboBox<String>(items);

        dropdown.setSelectedIndex(index);
        dropdown.addItemListener(event);

        return dropdown;
    }
}
