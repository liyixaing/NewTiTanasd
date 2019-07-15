package lanjing.com.titan.request;

public class SaveOrUpdateRequest {
    /**
     * {
     * "phone": "1237",
     * "vcode": "1111",
     * "address": "xxx",
     * "tag": "aaa",
     * "remark": "222",
     * "addressId": "1"
     * }
     */

    private String phone;
    private String vcode;
    private String address;
    private String tag;
    private String remark;

    public SaveOrUpdateRequest(String phone, String vcode, String address, String tag, String remark) {
        this.phone = phone;
        this.vcode = vcode;
        this.address = address;
        this.tag = tag;
        this.remark = remark;
    }
}
