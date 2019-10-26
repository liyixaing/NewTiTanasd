package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class SaveOrUpdateRequest {


    private String phone;
    private String vcode;
    private String address;
    private String tag;
    private String remark;
    private int language;

    public SaveOrUpdateRequest(String phone, String vcode, String address, String tag, String remark) {
        this.phone = phone;
        this.vcode = vcode;
        this.address = address;
        this.tag = tag;
        this.remark = remark;
        this.language = Constant.LANGAGE;
    }
}
