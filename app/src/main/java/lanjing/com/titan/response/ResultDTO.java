package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/9.
 * 通用返回实体  类型相同即可
 */

public class ResultDTO {

    /**
     * msg : ok
     * code : 200
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
