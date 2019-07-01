package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/12.
 */

public class WalletDetailResponse {


    /**
     * msg : ok
     * code : 200
     * data : {"fid":115,"coinid":1,"cointype":"Titan","address":"99999906","frozennum":10.36,"sum":11408.617073,"coinnum":1398.257073,"locknum":0,"shiftnum":10000,"userkey":"99999906"}
     * history : [{"num":"-0.120000","remark":"手续费","Id":"1","state":"进行中","time":"2019-05-13 00:04:45","type":"0"},{"num":"-1.000000","remark":"手续费","Id":"3","state":"已完成","time":"2019-05-13 00:11:14","type":"0"}]
     * history2 : [{"coinNum1":"0.750000","coinNum2":"0.750000","time":"2019-05-17 18:10:26","type":"2"},{"coinNum1":"0.750000","coinNum2":"0.750000","time":"2019-05-17 18:13:28","type":"2"},{"coinNum1":"0.000000","coinNum2":"0.000000","time":"2019-05-17 18:13:28","type":"2"},{"coinNum1":"0.000000","coinNum2":"0.000000","time":"2019-05-17 18:13:31","type":"2"},{"coinNum1":"0.000000","coinNum2":"0.000000","time":"2019-05-17 18:13:32","type":"2"}]
     */

    private String msg;
    private int code;
    private DataBean data;
    private List<HistoryBean> history;
    private List<History2Bean> history2;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<HistoryBean> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryBean> history) {
        this.history = history;
    }

    public List<History2Bean> getHistory2() {
        return history2;
    }

    public void setHistory2(List<History2Bean> history2) {
        this.history2 = history2;
    }

    public static class DataBean {
        /**
         * fid : 115
         * coinid : 1
         * cointype : Titan
         * address : 99999906
         * frozennum : 10.36
         * sum : 11408.617073
         * coinnum : 1398.257073
         * locknum : 0
         * shiftnum : 10000
         * userkey : 99999906
         */

        private int fid;
        private int coinid;
        private String cointype;
        private String address;
        private double frozennum;
        private double sum;
        private double coinnum;
        private double locknum;
        private double shiftnum;
        private String userkey;

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public int getCoinid() {
            return coinid;
        }

        public void setCoinid(int coinid) {
            this.coinid = coinid;
        }

        public String getCointype() {
            return cointype;
        }

        public void setCointype(String cointype) {
            this.cointype = cointype;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getFrozennum() {
            return frozennum;
        }

        public void setFrozennum(double frozennum) {
            this.frozennum = frozennum;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }

        public double getCoinnum() {
            return coinnum;
        }

        public void setCoinnum(double coinnum) {
            this.coinnum = coinnum;
        }

        public double getLocknum() {
            return locknum;
        }

        public void setLocknum(double locknum) {
            this.locknum = locknum;
        }

        public double getShiftnum() {
            return shiftnum;
        }

        public void setShiftnum(double shiftnum) {
            this.shiftnum = shiftnum;
        }

        public String getUserkey() {
            return userkey;
        }

        public void setUserkey(String userkey) {
            this.userkey = userkey;
        }
    }

    public static class HistoryBean {
        /**
         * num : -0.120000
         * remark : 手续费
         * Id : 1
         * state : 进行中
         * time : 2019-05-13 00:04:45
         * type : 0
         */

        private String num;
        private String remark;
        private String Id;
        private String state;

        public int getStatetype() {
            return statetype;
        }

        public void setStatetype(int statetype) {
            this.statetype = statetype;
        }

        private int statetype;
        private String time;
        private String type;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
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

    public static class History2Bean {
        /**
         * coinNum1 : 0.750000
         * coinNum2 : 0.750000
         * time : 2019-05-17 18:10:26
         * type : 2
         */

        private String coinNum1;
        private String coinNum2;
        private String time;
        private String type;

        public String getCoinNum1() {
            return coinNum1;
        }

        public void setCoinNum1(String coinNum1) {
            this.coinNum1 = coinNum1;
        }

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
    }
}
