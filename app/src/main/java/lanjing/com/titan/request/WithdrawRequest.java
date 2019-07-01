package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/23.
 */

public class WithdrawRequest {

    /**
     * welletId : 99999905
     * address : fafagagsgfnfklvdsfsmg
     * num : 1.23
     */

    private String welletId;
    private String address;
    private String num;


    public WithdrawRequest(String address,String welletId, String num) {
        this.address = address;
        this.welletId = welletId;
        this.num = num;
    }
}
