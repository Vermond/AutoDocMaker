package groupId.data;

/**
 * 메인 옵션 저장용 클래스
 */
public class MainOption {
    private String mainPath = "";
    private String logHeader = "log_";

    public String getMainPath() { return mainPath; }
    public void setMainPath(String value) { mainPath = value; }

    public String getLogHeader() { return logHeader; }
    public void setLogHeader(String value) { logHeader = value; }

    public MainOption() {}

    public MainOption(String path, String header) {
        mainPath = path;
        logHeader = header;
    }
}

