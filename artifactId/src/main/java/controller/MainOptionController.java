package controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import data.MainOption;
import data.Strings;

public class MainOptionController implements PropertyChangeInterface {
    private MainOption option;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MainOptionController() {
        // TODO : temp code
        option = new MainOption();
        option.setLogHeader("");
        option.setMainPath("");
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);        
    }

    public String getMainPath() { return option.getMainPath(); }
    public String getLogHeader() { return option.getLogHeader(); }

    public void setMainPath(String value) {
        var old = option.getMainPath();
        option.setMainPath(value);
        support.firePropertyChange(Strings.defaultPathVar, old, value);
    }
    public void setLogHeader(String value) {
        var old = option.getLogHeader();
        option.setLogHeader(value);
        support.firePropertyChange(Strings.defaultLogPrefixVar, old, value);
    }

    public void saveOption(String filePath) {
        JsonController.writeMainOption(option, filePath);
    }
}