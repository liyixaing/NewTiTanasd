package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/24.
 */

public class CoinDealResponse {

    /**
     * msg : ok
     * code : 200
     * data : [{"fee":"0.0","coinnum1":"0.998","coinmun2":"0.14071799999999998","type":"1","updatetime":"2019-05-23 22:50:49"},{"fee":"0.0","coinnum1":"0.998","coinmun2":"0.1370254","type":"1","updatetime":"2019-05-23 22:50:45"}]
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
         * fee : 0.0
         * coinnum1 : 0.998
         * coinmun2 : 0.14071799999999998
         * type : 1
         * updatetime : 2019-05-23 22:50:49
         */

        private String fee;
        private String coinnum1;
        private String coinmun2;
        private String type;
        private String updatetime;

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
    }
}
