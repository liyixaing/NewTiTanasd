package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/14.
 */

public class SixTradeResponse {


    private double tt_price_usd;
    private String msg;
    private int code;
    private double bar_price_usd;
    private double tt_price_cny;
    private double bar_price_cny;
    private List<BuydataBean> buydata;
    private List<SelldataBean> selldata;

    public double getTt_price_usd() {
        return tt_price_usd;
    }

    public void setTt_price_usd(double tt_price_usd) {
        this.tt_price_usd = tt_price_usd;
    }

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

    public double getBar_price_usd() {
        return bar_price_usd;
    }

    public void setBar_price_usd(double bar_price_usd) {
        this.bar_price_usd = bar_price_usd;
    }

    public double getTt_price_cny() {
        return tt_price_cny;
    }

    public void setTt_price_cny(double tt_price_cny) {
        this.tt_price_cny = tt_price_cny;
    }

    public double getBar_price_cny() {
        return bar_price_cny;
    }

    public void setBar_price_cny(double bar_price_cny) {
        this.bar_price_cny = bar_price_cny;
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


        private int fid;
        private double rest;
        private String createtime;
        private String fuser;
        private String hangOrderWay;
        private double fee;
        private String tradenum;
        private String type;
        private String releasenum;
        private String coinid;
        private String cointype;
        private String sourceCoin;
        private double price;
        private double sourceAmount;
        private double coinnum;
        private String state;
        private String updatetime;
        private String isreal;

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

        public String getHangOrderWay() {
            return hangOrderWay;
        }

        public void setHangOrderWay(String hangOrderWay) {
            this.hangOrderWay = hangOrderWay;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public String getTradenum() {
            return tradenum;
        }

        public void setTradenum(String tradenum) {
            this.tradenum = tradenum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReleasenum() {
            return releasenum;
        }

        public void setReleasenum(String releasenum) {
            this.releasenum = releasenum;
        }

        public String getCoinid() {
            return coinid;
        }

        public void setCoinid(String coinid) {
            this.coinid = coinid;
        }

        public String getCointype() {
            return cointype;
        }

        public void setCointype(String cointype) {
            this.cointype = cointype;
        }

        public String getSourceCoin() {
            return sourceCoin;
        }

        public void setSourceCoin(String sourceCoin) {
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getIsreal() {
            return isreal;
        }

        public void setIsreal(String isreal) {
            this.isreal = isreal;
        }
    }

    public static class SelldataBean {

        private int fid;
        private double rest;
        private String createtime;
        private String fuser;
        private String hangOrderWay;
        private double fee;
        private String tradenum;
        private String type;
        private String releasenum;
        private String coinid;
        private String cointype;
        private double price;
        private double coinnum;
        private String state;
        private String updatetime;
        private String isreal;

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

        public String getHangOrderWay() {
            return hangOrderWay;
        }

        public void setHangOrderWay(String hangOrderWay) {
            this.hangOrderWay = hangOrderWay;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public String getTradenum() {
            return tradenum;
        }

        public void setTradenum(String tradenum) {
            this.tradenum = tradenum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReleasenum() {
            return releasenum;
        }

        public void setReleasenum(String releasenum) {
            this.releasenum = releasenum;
        }

        public String getCoinid() {
            return coinid;
        }

        public void setCoinid(String coinid) {
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getIsreal() {
            return isreal;
        }

        public void setIsreal(String isreal) {
            this.isreal = isreal;
        }
    }
}
