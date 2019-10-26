package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/9.
 */

public class LoginResponse {


    private String msg;
    private int code;
    public Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String Invitacode;
        private String token;

        public String getInvitacode() {
            return Invitacode;
        }

        public void setInvitacode(String invitacode) {
            Invitacode = invitacode;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


}
