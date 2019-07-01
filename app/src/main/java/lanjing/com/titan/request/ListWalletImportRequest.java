package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/14.
 */

public class ListWalletImportRequest {

    /**
     * help : fjsfkd，fdsfds，fsh
     * keys : e6a252b7152a4e43b4ef3e44b2be9054
     */

    private String help;
    private String keys;
    private String device;


    public ListWalletImportRequest(String help, String keys, String device) {
        this.help = help;
        this.keys = keys;
        this.device = device;
    }
}
