package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class deleterRequest {
    public String addressId;
    private int language;

    public deleterRequest(String addressId) {
        this.addressId = addressId;
        this.language = Constant.LANGAGE;
    }
}
