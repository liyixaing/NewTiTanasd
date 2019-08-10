package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/24.
 */

public class CoinDealResponse {


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

        private String coin1;
        private String fee;
        private String coinnum1;
        private String coinmun2;
        private String type;
        private String updatetime;
        private String id;

        public String getCoin1() {
            return coin1;
        }

        public void setCoin1(String coin1) {
            this.coin1 = coin1;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getCoinnum1() {
            return coinnum1;
        }

        public void setCoinnum1(String coinnum1) {
            this.coinnum1 = coinnum1;
        }

        public String getCoinmun2() {
            return coinmun2;
        }

        public void setCoinmun2(String coinmun2) {
            this.coinmun2 = coinmun2;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
