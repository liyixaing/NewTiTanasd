package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/9.
 */

public class ImportWalletResponse {


    /**
     * msg : ok
     * code : 200
     * userName : 134454
     * userId : 60
     */

    private String msg;
    private int code;
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
