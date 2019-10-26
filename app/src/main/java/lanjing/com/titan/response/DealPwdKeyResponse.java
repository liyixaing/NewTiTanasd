package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/12.
 */

public class DealPwdKeyResponse {

    /**
     * msg : ok
     * code : 200
     * welletkey : e6a252b7152a4e43b4ef3e44b2be9054
     */

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

    public static class Data{
        private String welletkey;

        public String getWelletkey() {
            return welletkey;
        }

        public void setWelletkey(String welletkey) {
            this.welletkey = welletkey;
        }
    }

}
