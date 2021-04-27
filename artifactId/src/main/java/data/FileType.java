package data;

//I know there is something another nice way to matching enum with int/string
//But this enum is very short, so I don`t need to implement that way
//If you want it, check link : https://www.baeldung.com/java-cast-int-to-enum
public enum FileType {
    FIXED (0),
    GENERATE (1),
    NONE(-1);

    private int value;

    private FileType(int value) {
        this.value = value;
    }

    public static FileType fromInt(int value) {
        switch (value) {
            case 0:
            return FileType.FIXED;
            case 1:
            return FileType.GENERATE;
            default:
            return FileType.NONE;
        }
    }

    public int toInt() {
        switch(this) {
            case FIXED:
            return 0;
            case GENERATE:
            return 1;
            default:
            return -1;
        }
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        switch(this) {
            case FIXED:
            return "FIXED";
            case GENERATE:
            return "GENERATE";
            default:
            return "NONE";
        }
    }
}