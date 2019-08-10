package lanjing.com.titan.response;

public class ConvertConfigResponse {

    private String msg;
    private int code;
    public DataBean data;

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
        private String bar_price_usdt;
        private String tt_price_usdt;
        private String convert_rate;
        private String convert_fee_rate;
        private String convert_switch;

        public String getBar_price_usdt() {
            return bar_price_usdt;
        }

        public void setBar_price_usdt(String bar_price_usdt) {
            this.bar_price_usdt = bar_price_usdt;
        }

        public String getTt_price_usdt() {
            return tt_price_usdt;
        }

        public void setTt_price_usdt(String tt_price_usdt) {
            this.tt_price_usdt = tt_price_usdt;
        }

        public String getConvert_rate() {
            return convert_rate;
        }

        public void setConvert_rate(String convert_rate) {
            this.convert_rate = convert_rate;
        }

        public String getConvert_fee_rate() {
            return convert_fee_rate;
        }

        public void setConvert_fee_rate(String convert_fee_rate) {
            this.convert_fee_rate = convert_fee_rate;
        }

        public String getConvert_switch() {
            return convert_switch;
        }

        public void setConvert_switch(String convert_switch) {
            this.convert_switch = convert_switch;
        }
    }

}
