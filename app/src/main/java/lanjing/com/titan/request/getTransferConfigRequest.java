package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class getTransferConfigRequest {
    private int coin;
    private int language;

    public getTransferConfigRequest(int coin) {
        this.coin = coin;
        this.language = Constant.LANGAGE;
    }
}
