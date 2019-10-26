package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/15.
 */

public class RecallRequest {

    /**
     * Id : 17
     */

    private String Id;
    private int language;

    public RecallRequest(String id) {
        Id = id;
        this.language = Constant.LANGAGE;
    }
}
