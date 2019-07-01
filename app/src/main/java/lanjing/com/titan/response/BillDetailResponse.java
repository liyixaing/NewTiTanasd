package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/13.
 * 获取钱包详情数据  返回实体
 */

public class BillDetailResponse {


    /**
     * msg : ok
     * code : 200
     * history : {"keys":"fdsf","num":"1.630000","state":"已完成","time":"2019-05-13 04:06:25","type":"交易释放"}
     */

    private String msg;
    private int code;
    private HistoryBean history;

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

    public HistoryBean getHistory() {
        return history;
    }

    public void setHistory(HistoryBean history) {
        this.history = history;
    }

    public static class HistoryBean {
        /**
         * keys : fdsf
         * num : 1.630000
         * state : 已完成
         * time : 2019-05-13 04:06:25
         * type : 交易释放
         */

        private String keys;
        private String num;
        private String state;
        private String time;
        private String type;
        private String toTag;
        private String fromAddress;
        private String toAddress;

        public String getToTag() {
            return toTag;
        }

        public void setToTag(String toTag) {
            this.toTag = toTag;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getKeys() {
            return keys;
        }

        public void setKeys(String keys) {
            this.keys = keys;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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
