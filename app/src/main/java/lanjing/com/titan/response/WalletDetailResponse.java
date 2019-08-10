package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/12.
 */

public class WalletDetailResponse {


    private String msg;
    private int code;
    private DataBean data;

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

    public static class DataBean {

        public Wellet wellet;

        public Wellet getWellet() {
            return wellet;
        }

        public void setWellet(Wellet wellet) {
            this.wellet = wellet;
        }

        public static class Wellet {
            private String address;
            private int coinid;
            private double coinnum;
            private String cointype;
            private int fid;
            private double frozennum;
            private double locknum;
            private double shiftnum;
            private String userkey;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCoinid() {
                return coinid;
            }

            public void setCoinid(int coinid) {
                this.coinid = coinid;
            }

            public double getCoinnum() {
                return coinnum;
            }

            public void setCoinnum(double coinnum) {
                this.coinnum = coinnum;
            }

            public String getCointype() {
                return cointype;
            }

            public void setCointype(String cointype) {
                this.cointype = cointype;
            }

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public double getFrozennum() {
                return frozennum;
            }

            public void setFrozennum(double frozennum) {
                this.frozennum = frozennum;
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


    }
}
