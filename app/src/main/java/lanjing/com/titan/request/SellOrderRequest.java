package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class SellOrderRequest {
    String orderId;
    private int language;
    public SellOrderRequest(String orderId) {
        this.orderId = orderId;
        this.language = Constant.LANGAGE;

    }
}
