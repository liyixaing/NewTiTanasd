package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/24.
 */

public class WithdrawalResponse {

    /**
     * msg : ok
     * code : 200
     * data : [{"num":"1.000000","time":"2019-05-23 01:00:00","type":"2"}]
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
         * num : 1.000000
         * time : 2019-05-23 01:00:00
         * type : 2
         */

        private String num;
        private String time;
        private String type;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
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
    }
}
