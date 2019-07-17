package lanjing.com.titan.request;

public class FormatRequest {

    private int type;
    private int page;
    private int size;
    private int language;

    public FormatRequest(int type, int page, int size, int language) {
        this.type = type;
        this.page = page;
        this.size = size;
        this.language = language;

    }
}
