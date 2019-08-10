package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/17.
 * 买入或者卖出  请求实体
 */

public class BuyOrSellRequest {

    /**
     * coinNum : 1.23
     * type : 1
     * price : 2.63
     */

    private String coin;
    private String coinNum;
    private String type;


    public BuyOrSellRequest(String coin, String coinNum, String type) {
        this.coin = coin;
        this.coinNum = coinNum;
        this.type = type;

    }
}
