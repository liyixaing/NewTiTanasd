package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/24.
 */

public class AwardResponse {

    /**
     * msg : ok
     * code : 200
     * data : [{"coinNum2":"29329.714001","time":"2019-05-23 00:00:00","type":"1","Cointype2":"2"},{"coinNum2":"20593.203448","time":"2019-05-24 00:00:00","type":"1","Cointype2":"2"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * coinNum2 : 29329.714001
         * time : 2019-05-23 00:00:00
         * type : 1
         * Cointype2 : 2
         */

        private String coinNum2;
        private String time;
        private String type;
        private String Cointype2;

        public String getCoinNum2() {
            return coinNum2;
        }

        public void setCoinNum2(String coinNum2) {
            this.coinNum2 = coinNum2;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCointype2() {
            return Cointype2;
        }

        public void setCointype2(String Cointype2) {
            this.Cointype2 = Cointype2;
        }
    }
}
