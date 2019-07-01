package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/20.
 */

public class InterDealResponse {

    /**
     * msg : ok
     * code : 200
     * isauto : 1
     */

    private String msg;
    private int code;
    private int isauto;

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

    public int getIsauto() {
        return isauto;
    }

    public void setIsauto(int isauto) {
        this.isauto = isauto;
    }
}
