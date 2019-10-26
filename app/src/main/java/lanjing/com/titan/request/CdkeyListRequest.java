package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class CdkeyListRequest {

    public int page;
    public int size;
    private int language;

    public CdkeyListRequest(int page, int size) {
        this.page = page;
        this.size = size;
        this.language = Constant.LANGAGE;
    }
}
