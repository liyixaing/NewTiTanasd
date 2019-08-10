package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/24.
 */

public class WithdrawalRequest {

    /**
     * page : 1
     * size : 2
     */

    private String coin;
    private String type;
    private String page;
    private String size;

    public WithdrawalRequest(String coin, String type, String page, String size) {
        this.coin = coin;
        this.type = type;
        this.page = page;
        this.size = size;
    }

}
