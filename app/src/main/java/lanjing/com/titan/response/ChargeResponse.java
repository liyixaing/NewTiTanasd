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
    private String address;
    private int code;
    private String keys;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
