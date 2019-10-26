package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class ActiveRequest {

    private String cdkey;
    private int language;

    public ActiveRequest(String cdkey) {
        this.cdkey = cdkey;
        this.language = Constant.LANGAGE;
    }
}
