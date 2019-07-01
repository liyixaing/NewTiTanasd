package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/25.
 */

public class FeedBackListRequest {

    /**
     * page : 1
     * pagesize : 2
     */

    private String page;
    private String pagesize;

    public FeedBackListRequest(String page, String pagesize) {
        this.page = page;
        this.pagesize = pagesize;
    }

}
