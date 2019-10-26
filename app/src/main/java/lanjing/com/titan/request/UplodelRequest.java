package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class UplodelRequest {
    private String url;
    private int language;

    public UplodelRequest(String url) {
        this.url = url;
        this.language = Constant.LANGAGE;
    }
}
