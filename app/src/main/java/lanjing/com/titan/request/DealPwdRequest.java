package lanjing.com.titan.request;

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


    public DealPwdRequest(String password, String type) {
        this.password = password;
        this.type = type;
    }
}
