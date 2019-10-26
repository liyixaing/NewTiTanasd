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
        public String agreement;

        public String getAgreement() {
            return agreement;
        }

        public void setAgreement(String agreement) {
            this.agreement = agreement;
        }
    }
}
