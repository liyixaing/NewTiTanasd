package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/12.
 */

public class WalletDetailRequest {


    /**
     * welletfid : 90
     * type : 0
     * page : 1
     * size : 5
     */

    private String welletfid;
    private String type;
    private String page;
    private String size;


    public WalletDetailRequest(String welletfid, String type, String page, String size) {
        this.welletfid = welletfid;
        this.type = type;
        this.page = page;
        this.size = size;
    }
}
