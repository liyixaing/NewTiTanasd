package lanjing.com.titan.request;

public class HistoryListRequest {
    private String coin;
    private String type;
    private String page;
    private String size;

    public HistoryListRequest(String coin, String type, String page, String size) {
        this.coin = coin;
        this.type = type;
        this.page = page;
        this.size = size;
    }
}
