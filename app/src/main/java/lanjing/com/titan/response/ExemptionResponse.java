package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/23.
 */

public class ExemptionResponse {

    /**
     * msg : ok
     * code : 200
     * ExemptionCH : http://114.55.165.42:8081/titan/html/readme.html
     * ExemptionEN : http://114.55.165.42:8081/titan/html/readme-en.html
     */

    private String msg;
    private int code;
    private String ExemptionCH;
    private String ExemptionEN;

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

    public String getExemptionCH() {
        return ExemptionCH;
    }

    public void setExemptionCH(String ExemptionCH) {
        this.ExemptionCH = ExemptionCH;
    }

    public String getExemptionEN() {
        return ExemptionEN;
    }

    public void setExemptionEN(String ExemptionEN) {
        this.ExemptionEN = ExemptionEN;
    }
}
