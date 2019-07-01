package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/24.
 */

public class CoinDealRequest {

    /**
     * page : 1
     * size : 2
     */

    private String page;
    private String size;

    public CoinDealRequest(String page, String size) {
        this.page = page;
        this.size = size;
    }

}
