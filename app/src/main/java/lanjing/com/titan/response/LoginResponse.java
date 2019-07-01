package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/9.
 */

public class LoginResponse {


    /**
     * msg : ok
     * code : 200
     * Invitacode :
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTc0NjExMDg3NDUsInBheWxvYWQiOiJcIjk1MGQzMDViZjA5MzQ2Yzc5Y2Q4NDQxNTg0MjFiZmJhXCIifQ.FYvWwfatlaJx9tNfbM8jSObm3d1_4VbBqOl6tTPmrCw
     */

    private String msg;
    private int code;
    private String Invitacode;
    private String token;

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

    public String getInvitacode() {
        return Invitacode;
    }

    public void setInvitacode(String Invitacode) {
        this.Invitacode = Invitacode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
