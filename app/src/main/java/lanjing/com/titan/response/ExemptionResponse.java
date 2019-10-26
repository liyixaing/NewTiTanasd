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
        private String exemption;

        public String getExemption() {
            return exemption;
        }

        public void setExemption(String exemption) {
            this.exemption = exemption;
        }
    }
}
