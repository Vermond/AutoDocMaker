package data;

public class GenerateFileInfo {
    private String filterRegExr;
    private String fillterOutputRegExr;
    private String specialDescOption;
    private String specialDescContent;
    private String headerContent;
    private String footerContent;

    public GenerateFileInfo() {
        filterRegExr = "";
        fillterOutputRegExr = "";
        specialDescOption = "";
        specialDescContent = "";
        headerContent = "";
        footerContent = "";
    }

    public GenerateFileInfo(String _filter, String _filterOutput, String _specialOption, String _specialDesc, String _header, String _footer) {
        if (!isSameLine(_specialOption, _specialDesc)) {
            throw new IllegalArgumentException("Do not match number of lines between option and desc");
        }

        filterRegExr = _filter;
        fillterOutputRegExr = _filterOutput;
        specialDescOption = _specialOption;
        specialDescContent = _specialDesc;
        headerContent = _header;
        footerContent = _footer;
    }

    public String getFilter() { return filterRegExr; }
    public String getFilterOutput() { return fillterOutputRegExr; }
    public String getSpecialOption() { return specialDescOption; }
    public String getSpecialContent() { return specialDescContent; }
    public String getHeader() { return headerContent; }
    public String getFooter() { return footerContent; }

    public void setFilter(String value) { filterRegExr = value; }
    public void setFilterOutput(String value) { fillterOutputRegExr = value; }
    public void setSpecial(String option, String content) {
        if (!isSameLine(option, content)) {
            throw new IllegalArgumentException("Do not match number of lines between option and desc");
        }
        specialDescOption = option;
        specialDescContent = content;
    }        
    public void setHeader(String value) { headerContent = value; }
    public void setFooter(String value) { footerContent = value; }

    private boolean isSameLine(String a, String b) {
        return countLines(a) == countLines(b);
    }

    private long countLines(String s) {
        return s.chars().filter(ch -> ch == '\n').count();
    }
}
