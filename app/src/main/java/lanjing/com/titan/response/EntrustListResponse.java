package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/15.
 */

public class EntrustListResponse {


    /**
     * msg : ok
     * code : 200
     * data : [{"fid":16,"coinid":1,"rest":1.23,"cointype":"titanusd","createtime":"2019-05-14 11:08:22","fuser":"99999928","price":2.63,"tradenum":0,"coinnum":1.23,"state":1,"type":1,"updatetime":"2019-05-14 11:08:22"},{"fid":3,"coinid":1,"rest":10,"cointype":"Titan","createtime":"2019-05-13 02:36:59","fuser":"99999928","price":9.12,"tradenum":0,"coinnum":10,"state":1,"type":1,"updatetime":"2019-05-13 02:37:02"},{"fid":5,"coinid":1,"rest":10,"cointype":"Titan","createtime":"2019-05-13 02:36:59","fuser":"99999928","price":8.634,"tradenum":0,"coinnum":20,"state":1,"type":1,"updatetime":"2019-05-13 02:37:02"},{"fid":6,"coinid":1,"rest":20,"cointype":"Titan","createtime":"2019-05-13 02:36:59","fuser":"99999928","price":7.639,"tradenum":0,"coinnum":20,"state":1,"type":1,"updatetime":"2019-05-13 02:37:02"},{"fid":7,"coinid":1,"rest":10,"cointype":"Titan","createtime":"2019-05-13 02:36:59","fuser":"99999928","price":9.12,"tradenum":0,"coinnum":10,"state":1,"type":2,"updatetime":"2019-05-13 02:37:02"}]
     */

    private String msg;
    private int code;
    private String stallThreshold;

    public String getStallThreshold() {
        return stallThreshold;
    }

    public void setStallThreshold(String stallThreshold) {
        this.stallThreshold = stallThreshold;
    }

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

        private int fid;
        private double rest;
        private String createtime;
        private String fuser;
        private int hangOrderWay;
        private double fee;
        private double tradenum;
        private int type;
        private double releasenum;
        private int coinid;
        private String cointype;
        private int sourceCoin;
        private double price;
        private double sourceAmount;
        private double coinnum;
        private int state;
        private String updatetime;
        private int isreal;

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public double getRest() {
            return rest;
        }

        public void setRest(double rest) {
            this.rest = rest;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getFuser() {
            return fuser;
        }

        public void setFuser(String fuser) {
            this.fuser = fuser;
        }

        public int getHangOrderWay() {
            return hangOrderWay;
        }

        public void setHangOrderWay(int hangOrderWay) {
            this.hangOrderWay = hangOrderWay;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public double getTradenum() {
            return tradenum;
        }

        public void setTradenum(double tradenum) {
            this.tradenum = tradenum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getReleasenum() {
            return releasenum;
        }

        public void setReleasenum(double releasenum) {
            this.releasenum = releasenum;
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

        public int getSourceCoin() {
            return sourceCoin;
        }

        public void setSourceCoin(int sourceCoin) {
            this.sourceCoin = sourceCoin;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getSourceAmount() {
            return sourceAmount;
        }

        public void setSourceAmount(double sourceAmount) {
            this.sourceAmount = sourceAmount;
        }

        public double getCoinnum() {
            return coinnum;
        }

        public void setCoinnum(double coinnum) {
            this.coinnum = coinnum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public int getIsreal() {
            return isreal;
        }

        public void setIsreal(int isreal) {
            this.isreal = isreal;
        }
    }
}
