package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/12.
 */

public class WalletDetailRequest {


    private String coin;
    private int language;

    public WalletDetailRequest(String coin) {
        this.coin = coin;
        this.language = Constant.LANGAGE;
    }
}
