package controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeInterface {

    public void addPropertyChangeListener(PropertyChangeListener listener);
    public void removePropertyChangeListener(PropertyChangeListener listener);
}
