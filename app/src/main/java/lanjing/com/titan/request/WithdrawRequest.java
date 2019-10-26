package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/23.
 */

public class WithdrawRequest {

    /**
     * welletId : 99999905
     * address : fafagagsgfnfklvdsfsmg
     * num : 1.23
     */
    private String coin;
    private String welletId;//标签
    private String address;//地址
    private String num;//数量
    private int language;

    public WithdrawRequest(String coin, String address, String welletId, String num) {
        this.coin = coin;
        this.address = address;
        this.welletId = welletId;
        this.num = num;
        this.language = Constant.LANGAGE;
    }
}
