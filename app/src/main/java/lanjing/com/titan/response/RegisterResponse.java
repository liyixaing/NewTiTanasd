package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/9.
 */

public class RegisterResponse {


    /**
     * msg : ok
     * code : 200
     * userkey : ca5f6768410f4d059432febb2bc453a8
     */

    private String msg;
    private int code;

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
}
