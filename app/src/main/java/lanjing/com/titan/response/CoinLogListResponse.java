package lanjing.com.titan.response;

import java.util.List;

public class CoinLogListResponse {
    private String msg;
    private int code;
    public List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String changeAmount;
        private String changeCoin;
        private String changeDesc;
        private String changeTime;
        private String changeType;
        private String id;
        private String orderId;
        private String userKey;

        public String getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(String changeAmount) {
            this.changeAmount = changeAmount;
        }

        public String getChangeCoin() {
            return changeCoin;
        }

        public void setChangeCoin(String changeCoin) {
            this.changeCoin = changeCoin;
        }

        public String getChangeDesc() {
            return changeDesc;
        }

        public void setChangeDesc(String changeDesc) {
            this.changeDesc = changeDesc;
        }

        public String getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(String changeTime) {
            this.changeTime = changeTime;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }
    }
}
