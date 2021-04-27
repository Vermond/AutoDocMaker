package controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import data.FileData;
import data.FixedFileInfo;
import data.GenerateFileInfo;
import data.Strings;

//I feel this way of implementation is a little old because of too much code which is similar
//But I will keep this now. Finding another way is not a good way rigth now and it will takes some long time.
public class FileDataController {
    private ArrayList<FileData> dataList;
    private int currentIndex = 0;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FileDataController() {
        dataList = new ArrayList<FileData>();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public FileData getCurrentData() {
        return dataList.get(currentIndex);
    }

    public void setCurrentData(int index) {
        currentIndex = index;
    }

    public void addData() {
        dataList.add(new FileData());
        currentIndex = dataList.size() - 1;
    }

    public void addData(String dataPath) {
        dataList.add(JsonController.readFileData(dataPath));
        currentIndex = dataList.size() - 1;
    }

    public void removeCurrentData() {
        dataList.remove(currentIndex);
        currentIndex = currentIndex == 0 ? 0 : currentIndex - 1;
    }


    public String getFileName() { return getCurrentData().getFileName(); }
    public String getFilePath() { return getCurrentData().getFilePath(); }
    public int getFileType() { return getCurrentData().getFileType(); }
    public FixedFileInfo getFixedFileInfo() { return getCurrentData().getFixedFileInfo(); }
    public GenerateFileInfo getGenerateFileInfo() { return getCurrentData().getGenerateFileInfo(); }

    public void setFileName(String value) {
        var oldValue = getFileName();
        getCurrentData().setFileName(value);
        support.firePropertyChange(Strings.fileNameVar, oldValue, value);
    }
    public void setFilePath(String value) {
        var oldValue = getFilePath();
        getCurrentData().setFilePath(value);
        support.firePropertyChange(Strings.filePathVar, oldValue, value);
    }
    public void setFileType(int value) {
        var oldValue = getFileType();
        getCurrentData().setFileType(value);
        support.firePropertyChange(Strings.fileTypeVar, oldValue, value);
    }
    public void setFixedFileInfo(FixedFileInfo value) {
        var oldValue = getFixedFileInfo();
        getCurrentData().setFixedFileInfo(value);
        support.firePropertyChange(Strings.fileTypeFixedVar, oldValue, value);
    }
    public void setGenerateFileInfo(GenerateFileInfo value) {
        var oldValue = getGenerateFileInfo();
        getCurrentData().setGenerateFileInfo(value);
        support.firePropertyChange(Strings.fileTypeAutoVar, oldValue, value);
    }

    public String getContent() { return getFixedFileInfo().getContent(); }

    public void setContent(String value) {
        var oldValue = getContent();
        getFixedFileInfo().setContent(value);
        support.firePropertyChange(Strings.contentVar, oldValue, value);
    }

    public String getFilter() { return getGenerateFileInfo().getFilter(); }
    public String getFilterOutput() { return getGenerateFileInfo().getFilterOutput(); }
    public String getSpecialOption() { return getGenerateFileInfo().getSpecialContent(); }
    public String getSpecialContent() { return getGenerateFileInfo().getSpecialContent(); }
    public String getHeader() { return getGenerateFileInfo().getHeader(); }
    public String getFooter() { return getGenerateFileInfo().getFooter(); }

    public void setFilter(String value) {
        var oldValue = getFilter();
        getGenerateFileInfo().setFilter(value);
        support.firePropertyChange(Strings.filterRuleVar, oldValue, value);
    }
    public void setFilterOutput(String value) {
        var oldValue = getFilterOutput();
        getGenerateFileInfo().setFilterOutput(value);
        support.firePropertyChange(Strings.filterOutputVar, oldValue, value);
    }
    public void setSpecial(String option, String content) throws IllegalArgumentException{
        ArrayList<String> oldValue = new ArrayList<String>();
        oldValue.add(getSpecialOption());
        oldValue.add(getSpecialContent());

        ArrayList<String> newValue = new ArrayList<String>();
        oldValue.add(option);
        oldValue.add(content);
        
        getGenerateFileInfo().setSpecial(option, content);

        support.firePropertyChange(Strings.specialDescVar, oldValue, newValue);
    }        
    public void setHeader(String value) {
        var oldValue = getHeader();
        getGenerateFileInfo().setHeader(value);
        support.firePropertyChange(Strings.prefixHeaderVar, oldValue, value);
    }
    public void setFooter(String value) {
        var oldValue = getFooter();
        getGenerateFileInfo().setFooter(value);
        support.firePropertyChange(Strings.prefixEndVar, oldValue, value);
    }

}
