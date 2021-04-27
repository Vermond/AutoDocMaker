package data;

public class FileData {
    public static int FILE_FIXED = 0;
    public static int FILE_GENERATE = 1;

    private String fileName;
    private String filePath;
    private int fileType;
    private FixedFileInfo fixedInfo;
    private GenerateFileInfo generateInfo;

    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
    public int getFileType() { return fileType; }
    public FixedFileInfo getFixedFileInfo() { return fixedInfo; }
    public GenerateFileInfo getGenerateFileInfo() { return generateInfo; }

    public void setFileName(String value) { fileName = value; }
    public void setFilePath(String value) { filePath = value; }
    public void setFileType(int value) { fileType = value; }
    public void setFixedFileInfo(FixedFileInfo value) { fixedInfo = value; }
    public void setGenerateFileInfo(GenerateFileInfo value) { generateInfo = value; }
}