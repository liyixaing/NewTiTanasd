package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/14.
 * 充币  返回实体
 */

public class ChargeResponse {

    /**
     * msg : ok
     * address : fafagagsgfnfklvdsfsmg
     * code : 200
     * keys : 99999928
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

    public static class Data {
        private String address;
        private String keys;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getKeys() {
            return keys;
        }

        public void setKeys(String keys) {
            this.keys = keys;
        }
    }

}
