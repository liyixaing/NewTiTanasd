package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class AddressRequest {
    private String page;
    private String size;
    private int language;

    public AddressRequest(String page, String size) {
        this.page = page;
        this.size = size;
        this.language = Constant.LANGAGE;
    }

}
