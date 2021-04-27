package data;

public class FileData {
    public static final int FILE_FIXED = 0;
    public static final int FILE_GENERATE = 1;
    
    private String dataName;

    private String fileName;
    private String filePath;
    private int fileType;
    private FixedFileInfo fixedInfo;
    private GenerateFileInfo generateInfo;

    public FileData() {}
    public FileData(String name) { dataName = name; }

    public String getDataName() { return dataName; }
    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
    public FileType getFileType() { return FileType.fromInt(fileType); }
    public FixedFileInfo getFixedFileInfo() { return fixedInfo; }
    public GenerateFileInfo getGenerateFileInfo() { return generateInfo; }

    public void setDataName(String value) { dataName = value; }
    public void setFileName(String value) { fileName = value; }
    public void setFilePath(String value) { filePath = value; }
    public void setFileType(FileType value) { fileType = value.toInt(); }
    public void setFixedFileInfo(FixedFileInfo value) { fixedInfo = value; }
    public void setGenerateFileInfo(GenerateFileInfo value) { generateInfo = value; }
}