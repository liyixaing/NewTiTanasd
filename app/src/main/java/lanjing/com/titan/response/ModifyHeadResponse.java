package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/10.
 */

public class ModifyHeadResponse {

    /**
     * msg : ok
     * picture : fdsfsf.jpg
     * code : 200
     */

    private String msg;
    private String picture;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
