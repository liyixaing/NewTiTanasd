package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/9.
 */

public class ImportWalletSetPwdRequest {

    /**
     * userId : 24
     * loginpassword : 13575136417
     * tradepassword : 2AC0F70BE07E11867CD796293E6A1211
     */

    private int userId;
    private String loginpassword;
    private String tradepassword;


    public ImportWalletSetPwdRequest(int userId, String loginpassword, String tradepassword) {
        this.userId = userId;
        this.loginpassword = loginpassword;
        this.tradepassword = tradepassword;
    }
}
