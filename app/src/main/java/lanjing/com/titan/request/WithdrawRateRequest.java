package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class WithdrawRateRequest {
    private int coin;
    private int language;

    public WithdrawRateRequest(int coin) {
        this.coin = coin;
        this.language = Constant.LANGAGE;
    }

}
