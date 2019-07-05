package lanjing.com.titan.response;

/**
 * Created by Administrator on 2019/7/5.
 */

public class SetNewPhoneResponse {

    /**
     * msg : ok
     * code : 200
     * userId : 15
     */

    private String msg;
    private int code;
    private String userId;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
