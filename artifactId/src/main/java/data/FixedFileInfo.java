package data;

public class FixedFileInfo {
    private String content;

    public FixedFileInfo() {
        content = "";
    }

    public FixedFileInfo(String _content) {
        content = _content;
    }

    public String getContent() { return content; }

    public void setContent(String value) { content = value; }
}