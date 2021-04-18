package groupId.data;

public class FileData {
    public static int FILE_FIXED = 0;
    public static int FILE_GENERATE = 1;

    private String fileName;
    private String filePath;
    private int fileType;
    private FixedFileInfo fixedInfo;
    private GenerateFileInfo generateInfo;


    String getFileName() { return fileName; }
    String getFilePath() { return filePath; }
    int getFileType() { return fileType; }
    FixedFileInfo getFixedFileInfo() { return fixedInfo; }
    GenerateFileInfo getGenerateFileInfo() { return generateInfo; }

    void setFileName(String value) { fileName = value; }
    void setFilePath(String value) { filePath = value; }
    void setFileType(int value) { fileType = value; }
    void setFixedFileInfo(FixedFileInfo value) { fixedInfo = value; }
    void setGenerateFileInfo(GenerateFileInfo value) { generateInfo = value; }


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

        boolean isSameLine(String a, String b) {
            return countLines(a) == countLines(b);
        }

        private int countLines(String s) {
            return (s + " ").split("\r?\n").length;
        }
    }

}
