package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/10.
 */

public class FriendListRequest {

    /**
     * page : 1
     * size : 5
     */

    private String page;
    private String size;


    public FriendListRequest(String page, String size) {
        this.page = page;
        this.size = size;
    }
}
