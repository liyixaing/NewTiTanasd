package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/14.
 */

public class InfoNoticeRequest {

    /**
     * type : 1
     * page : 1
     * size : 5
     */

    private String type;
    private String page;
    private String size;
    private int language;

    public InfoNoticeRequest(String type, String page, String size) {
        this.type = type;
        this.page = page;
        this.size = size;
        this.language = Constant.LANGAGE;
    }


}
