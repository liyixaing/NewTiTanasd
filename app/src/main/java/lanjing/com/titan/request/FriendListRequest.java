package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

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
    private int language;

    public FriendListRequest(String page, String size) {
        this.page = page;
        this.size = size;
        this.language = Constant.LANGAGE;
    }
}
