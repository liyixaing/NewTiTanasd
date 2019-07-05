package lanjing.com.titan.request;

/**
 * Created by Administrator on 2019/7/5.
 */

public class SetNewPhoneRequest {
    private String oldPhone;
    private String oldCode;
    private String newPhone;
    private String newColde;

    public SetNewPhoneRequest(String oldPhone, String oldCode, String newPhone, String newColde) {
        this.oldPhone = oldPhone;
        this.oldCode = oldCode;
        this.newPhone = newPhone;
        this.newColde = newColde;
    }
}
