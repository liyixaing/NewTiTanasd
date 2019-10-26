package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/15.
 */

public class EntrustListRequest {

    /**
     * page : 1
     * size : 5
     * state : 1
     */

    private String page;
    private String size;
    private String state;
    private int language;

    public EntrustListRequest(String page, String size, String state) {
        this.page = page;
        this.size = size;
        this.state = state;
        this.language = Constant.LANGAGE;
    }
}
