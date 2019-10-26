package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by Administrator on 2019/7/5.
 */

public class SetNewPhoneRequest {
    private String oldPhone;
    private String oldCode;
    private String newPhone;
    private String newCode;
    private int language;

    public SetNewPhoneRequest(String oldPhone, String oldCode, String newPhone, String newCode) {
        this.oldPhone = oldPhone;
        this.oldCode = oldCode;
        this.newPhone = newPhone;
        this.newCode = newCode;
        this.language = Constant.LANGAGE;
    }
}
