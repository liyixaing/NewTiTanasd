package lanjing.com.titan.response;

import java.util.List;

public class HistoryListResponse {
    private String msg;
    private int code;
    public List<mData> data;

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

    public List<mData> getData() {
        return data;
    }

    public void setData(List<mData> data) {
        this.data = data;
    }

    public static class mData {
        /**
         * "changeAmount": 673.5375685,//变化 量
         * "changeCoin": 1,            //变化币种
         * "changeDesc": "空投增加",    //变化描述
         * "changeTime": 1560939230,//变化时间
         * "changeType": 1,        //变化类型
         * "id": 227516,            //日志主键
         * "orderId": 87685,        //关联订单
         * "userKey": "2024506"    //用户标识
         */
        private double changeAmount;
        private int changeCoin;
        private String changeDesc;
        private String changeTime;
        private String changeType;
        private int id;
        private int orderId;
        private String userKey;

        public double getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(double changeAmount) {
            this.changeAmount = changeAmount;
        }

        public int getChangeCoin() {
            return changeCoin;
        }

        public void setChangeCoin(int changeCoin) {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
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
