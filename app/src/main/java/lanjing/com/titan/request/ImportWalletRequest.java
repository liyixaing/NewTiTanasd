package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/9.
 */

public class ImportWalletRequest {

    /**
     * help : 13575136417
     * keys : 2AC0F70BE07E11867CD796293E6A1211
     */

    private String help;
    private String keys;


    public ImportWalletRequest(String help, String keys) {
        this.help = help;
        this.keys = keys;
    }
}
