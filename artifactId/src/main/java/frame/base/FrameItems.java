package frame.base;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;

public class FrameItems {    

    public static JButton button(String title, ActionListener listener) {
        var btn = new JButton(title);

        btn.addActionListener(listener);

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

    public static JTextField textField(String inputDefault, DocumentListener listener) {
        JTextField input = new JTextField(inputDefault);
        input.getDocument().addDocumentListener(listener);

        return input;
    }

    public static JScrollPane multiTextField(String inputDefault, DocumentListener listener) {
        JTextArea input = new JTextArea(inputDefault);
        input.setLineWrap(true);
        input.getDocument().addDocumentListener(listener);

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
