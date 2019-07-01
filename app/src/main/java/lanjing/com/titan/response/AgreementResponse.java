package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/23.
 * 获取协议数据  返回实体
 */

public class AgreementResponse {

    /**
     * msg : ok
     * code : 200
     * AgreementEN : http://114.55.165.42:8081/titan/html/protocol-en.html
     * AgreementCH : http://114.55.165.42:8081/titan/html/protocol.html
     */

    private String msg;
    private int code;
    private String AgreementEN;
    private String AgreementCH;

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

    public String getAgreementEN() {
        return AgreementEN;
    }

    public void setAgreementEN(String AgreementEN) {
        this.AgreementEN = AgreementEN;
    }

    public String getAgreementCH() {
        return AgreementCH;
    }

    public void setAgreementCH(String AgreementCH) {
        this.AgreementCH = AgreementCH;
    }
}
