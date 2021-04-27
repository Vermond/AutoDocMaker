package listener;

import java.awt.event.*;

public class InputFocusListener implements FocusListener {

    @FunctionalInterface
    public interface FocusAction {
        public void action();    
    }

    private FocusAction onFocusGained;
    private FocusAction onFocusLost;

    public InputFocusListener(FocusAction onGained, FocusAction onLost) {
        onFocusGained = onGained;
        onFocusLost = onLost;
    }

    @Override
    public void focusGained(FocusEvent e) {
        onFocusGained.action();
    }

    @Override
    public void focusLost(FocusEvent e) {
        onFocusLost.action();        
    }
    
}
