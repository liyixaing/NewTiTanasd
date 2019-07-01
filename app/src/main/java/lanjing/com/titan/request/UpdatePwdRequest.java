package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/10.
 */

public class UpdatePwdRequest {


    /**
     * type : 2
     * oldpassword : 13246
     * code : 123456
     * newpassword : 2222223
     */

    private String type;
    private String oldpassword;
    private String code;
    private String newpassword;


    public UpdatePwdRequest(String type, String oldpassword, String code, String newpassword) {
        this.type = type;
        this.oldpassword = oldpassword;
        this.code = code;
        this.newpassword = newpassword;
    }
}
