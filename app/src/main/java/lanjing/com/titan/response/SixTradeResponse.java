package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/14.
 */

public class SixTradeResponse {


    /**
     * msg : ok
     * buydata : [{"fid":52,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":1,"releasenum":0,"coinid":1,"cointype":"titanusd","price":2.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-20 10:07:07","isreal":1},{"fid":53,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":1,"releasenum":0,"coinid":1,"cointype":"titanusd","price":2.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-20 10:07:07","isreal":1},{"fid":55,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":1,"releasenum":0,"coinid":1,"cointype":"titanusd","price":63.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-20 10:07:07","isreal":1},{"fid":57,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":1,"releasenum":0,"coinid":1,"cointype":"titanusd","price":2.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-20 10:07:07","isreal":1},{"fid":45,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":1,"releasenum":0,"coinid":1,"cointype":"titanusd","price":63.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-20 10:07:06","isreal":1},{"fid":47,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":1,"releasenum":0,"coinid":1,"cointype":"titanusd","price":2.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-20 10:07:06","isreal":1}]
     * code : 200
     * selldata : [{"fid":11,"rest":0,"createtime":"2019-05-13 02:36:59","fuser":"99999928","fee":0,"tradenum":10,"type":2,"releasenum":0,"coinid":1,"cointype":"Titan","price":9.12,"coinnum":10,"state":2,"updatetime":"2019-05-17 18:52:28"},{"fid":7,"rest":0,"createtime":"2019-05-13 02:36:59","fuser":"99999928","fee":0,"tradenum":10,"type":2,"releasenum":0,"coinid":1,"cointype":"Titan","price":9.12,"coinnum":10,"state":2,"updatetime":"2019-05-17 18:52:27"},{"fid":8,"rest":0,"createtime":"2019-05-13 02:36:59","fuser":"99999928","fee":0,"tradenum":10,"type":2,"releasenum":0,"coinid":1,"cointype":"Titan","price":9.369,"coinnum":15,"state":2,"updatetime":"2019-05-17 18:52:27"},{"fid":10,"rest":0,"createtime":"2019-05-13 02:36:59","fuser":"99999928","fee":0,"tradenum":20,"type":2,"releasenum":0,"coinid":1,"cointype":"Titan","price":7.639,"coinnum":20,"state":2,"updatetime":"2019-05-17 18:52:27"},{"fid":46,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":2,"releasenum":0,"coinid":1,"cointype":"titanusd","price":6.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-17 18:52:26","isreal":1},{"fid":49,"rest":0,"createtime":"2019-05-14 13:13:02","fuser":"99999913","fee":0,"tradenum":1.23,"type":2,"releasenum":0,"coinid":1,"cointype":"titanusd","price":3.63,"coinnum":1.23,"state":2,"updatetime":"2019-05-17 18:52:26","isreal":1}]
     * price : 2.36
     * CNYprice : 10.3645
     */

    private String msg;
    private int code;
    private double price;
    private double CNYprice;
    private List<BuydataBean> buydata;
    private List<SelldataBean> selldata;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCNYprice() {
        return CNYprice;
    }

    public void setCNYprice(double CNYprice) {
        this.CNYprice = CNYprice;
    }

    public List<BuydataBean> getBuydata() {
        return buydata;
    }

    public void setBuydata(List<BuydataBean> buydata) {
        this.buydata = buydata;
    }

    public List<SelldataBean> getSelldata() {
        return selldata;
    }

    public void setSelldata(List<SelldataBean> selldata) {
        this.selldata = selldata;
    }

    public static class BuydataBean {
        /**
         * fid : 52
         * rest : 0
         * createtime : 2019-05-14 13:13:02
         * fuser : 99999913
         * fee : 0
         * tradenum : 1.23
         * type : 1
         * releasenum : 0
         * coinid : 1
         * cointype : titanusd
         * price : 2.63
         * coinnum : 1.23
         * state : 2
         * updatetime : 2019-05-20 10:07:07
         * isreal : 1
         */

        private int fid;
        private double rest;
        private String createtime;
        private String fuser;
        private double fee;
        private double tradenum;
        private int type;
        private double releasenum;
        private int coinid;
        private String cointype;
        private double price;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

    public static class SelldataBean {
        /**
         * fid : 11
         * rest : 0
         * createtime : 2019-05-13 02:36:59
         * fuser : 99999928
         * fee : 0
         * tradenum : 10
         * type : 2
         * releasenum : 0
         * coinid : 1
         * cointype : Titan
         * price : 9.12
         * coinnum : 10
         * state : 2
         * updatetime : 2019-05-17 18:52:28
         * isreal : 1
         */

        private int fid;
        private double rest;
        private String createtime;
        private String fuser;
        private double fee;
        private double tradenum;
        private int type;
        private double releasenum;
        private int coinid;
        private String cointype;
        private double price;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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
