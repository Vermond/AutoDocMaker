package data;

import java.beans.*;

public class FileData {
    public static int FILE_FIXED = 0;
    public static int FILE_GENERATE = 1;

    private String fileName;
    private String filePath;
    private int fileType;
    private FixedFileInfo fixedInfo;
    private GenerateFileInfo generateInfo;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
    public int getFileType() { return fileType; }
    public FixedFileInfo getFixedFileInfo() { return fixedInfo; }
    public GenerateFileInfo getGenerateFileInfo() { return generateInfo; }

    public void setFileName(String value) { 
        var oldValue = fileName;
        fileName = value;
        support.firePropertyChange("fileName", oldValue, value);
    }
    public void setFilePath(String value) { filePath = value; }
    public void setFileType(int value) { fileType = value; }
    public void setFixedFileInfo(FixedFileInfo value) { fixedInfo = value; }
    public void setGenerateFileInfo(GenerateFileInfo value) { generateInfo = value; }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    class FixedFileInfo {
        private String content;

        FixedFileInfo(String _content) {
            content = _content;
        }

        String getContent() { return content; }

        void setContent(String value) { content = value; }
    }

    class GenerateFileInfo {
        private String filterRegExr;
        private String fillterOutputRegExr;
        private String specialDescOption;
        private String specialDescContent;
        private String headerContent;
        private String footerContent;

        GenerateFileInfo(String _filter, String _filterOutput, String _specialOptiom, String _specialDesc, String _header, String _footer) {
            if (isSameLine(_specialOptiom, _specialDesc)) {
                filterRegExr = _filter;
                fillterOutputRegExr = _filterOutput;
                specialDescOption = _specialOptiom;
                specialDescContent = _specialDesc;
                headerContent = _header;
                footerContent = _footer;
            } else {
                System.out.println("Error : 줄 개수가 안 맞음");
            }
        }


        String getFilter() { return filterRegExr; }
        String getFilterOutput() { return fillterOutputRegExr; }
        String getSpecialOption() { return specialDescOption; }
        String getSpecialContent() { return specialDescContent; }
        String getHeader() { return headerContent; }
        String getFooter() { return footerContent; }

        void setFilter(String value) { filterRegExr = value; }
        void setFilterOutput(String value) { fillterOutputRegExr = value; }
        void setSpecial(String option, String content) {
            if (isSameLine(option, content)) {
                specialDescOption = option;
                specialDescContent = content;
            } else {
                System.out.println("Error : 줄 개수가 안 맞음");
            }
        }        
        void setHeader(String value) { headerContent = value; }
        void setFooter(String value) { footerContent = value; }

        private boolean isSameLine(String a, String b) {
            return countLines(a) == countLines(b);
        }

        private int countLines(String s) {
            return s.split("\r?\n").length;
        }
    }

}