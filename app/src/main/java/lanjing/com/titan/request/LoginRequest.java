package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/9.
 */

public class LoginRequest {

    /**
     * userName : test2
     * loginPassword : 12345678
     */

    private String userName;
    private String loginPassword;
    private String device;
    private int language;

    public LoginRequest(String userName, String loginPassword, String device) {
        this.userName = userName;
        this.loginPassword = loginPassword;
        this.device = device;
        this.language = Constant.LANGAGE;
    }
}
