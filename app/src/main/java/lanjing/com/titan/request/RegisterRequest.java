package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/9.
 */

public class RegisterRequest {


    /**
     * username : test2
     * nickname : test
     * loginpassword : 123456
     * transpassword : 654321
     * Invitacode :
     */

    private String username;
    private String nickname;
    private String loginpassword;
    private String transpassword;
    private String Invitacode;
    private int language;

    public RegisterRequest(String username, String nickname, String loginpassword, String transpassword, String invitacode) {
        this.username = username;
        this.nickname = nickname;
        this.loginpassword = loginpassword;
        this.transpassword = transpassword;
        Invitacode = invitacode;
        this.language = Constant.LANGAGE;
    }
}
