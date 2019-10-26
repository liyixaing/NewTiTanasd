package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class CancelRequest {
    public String url;
    private int language;

    public CancelRequest(String url) {
        this.url = url;
        this.language = Constant.LANGAGE;
    }
}
