package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/10.
 */

public class SetPhoneRequest {

    /**
     * phonenum : 13575136417
     * code : 235478
     */

    private String phonenum;
    private String code;
    private int language;

    public SetPhoneRequest(String phonenum, String code) {
        this.phonenum = phonenum;
        this.code = code;
        this.language = Constant.LANGAGE;
    }
}
