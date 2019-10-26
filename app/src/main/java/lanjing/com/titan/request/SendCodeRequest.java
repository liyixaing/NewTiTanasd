package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/4/12.
 */

public class SendCodeRequest {

    /**
     * phone : 18200000001
     */

    private String phonenum;
    private int language;

    public SendCodeRequest(String phonenum) {
        this.phonenum = phonenum;
        this.language = Constant.LANGAGE;
    }
}
