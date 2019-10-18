package lanjing.com.titan.response;

public class WithdrawRateResponse {
    private String msg;
    private int code;
    public Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        private String price_usd;
        private String coin_name;
        private String coin_id;
        private String price_cny;
        private String price_usdt;

        public String getPrice_usd() {
            return price_usd;
        }

        public void setPrice_usd(String price_usd) {
            this.price_usd = price_usd;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getCoin_id() {
            return coin_id;
        }

        public void setCoin_id(String coin_id) {
            this.coin_id = coin_id;
        }

        public String getPrice_cny() {
            return price_cny;
        }

        public void setPrice_cny(String price_cny) {
            this.price_cny = price_cny;
        }

        public String getPrice_usdt() {
            return price_usdt;
        }

        public void setPrice_usdt(String price_usdt) {
            this.price_usdt = price_usdt;
        }
    }
}
