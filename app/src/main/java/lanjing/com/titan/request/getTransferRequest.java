package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class getTransferRequest {
    public String addressId;
    private int language;
    public getTransferRequest(String addressId) {
        this.addressId = addressId;
        this.language = Constant.LANGAGE;
    }
}
