package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/12.
 * 交易密码  请求实体
 */

public class DealPwdRequest {

    /**
     * password : 654321
     */

    private String password;
    private String type;
    private int language;

    public DealPwdRequest(String password, String type) {
        this.password = password;
        this.type = type;
        this.language = Constant.LANGAGE;
    }
}
