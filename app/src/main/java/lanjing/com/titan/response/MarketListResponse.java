package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/12.
 */

public class MarketListResponse {

    /**
     * mag : ok
     * code : 200
     * data : [{"symbol":"btcusdt","amount":"658376.8417460767","price":"9.0300000000","change":"0.035972053813730524","CNY":"2.0000000000"},{"symbol":"ethusdt","amount":"658376.8417460767","price":"9.0300000000","change":"0.02442404295860032","CNY":"1.0000000000"}]
     */

    private String mag;
    private int code;
    private List<DataBean> data;

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
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
         * symbol : btcusdt
         * amount : 658376.8417460767
         * price : 9.0300000000
         * change : 0.035972053813730524
         * CNY : 2.0000000000
         */

        private String symbol;
        private String amount;
        private String price;
        private String change;
        private String CNY;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getChange() {
            return change;
        }

        public void setChange(String change) {
            this.change = change;
        }

        public String getCNY() {
            return CNY;
        }

        public void setCNY(String CNY) {
            this.CNY = CNY;
        }
    }
}
