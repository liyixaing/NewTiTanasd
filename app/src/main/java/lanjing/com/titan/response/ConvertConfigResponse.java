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

        private String user_titan_amount;
        private String target_price_usdt;
        private String source_price_usdt;
        private String bar_price_usdt;
        private String tt_price_usdt;
        private String convert_rate;
        private String convert_fee_rate;
        private String convert_switch;

        public String getUser_titan_amount() {
            return user_titan_amount;
        }

        public void setUser_titan_amount(String user_titan_amount) {
            this.user_titan_amount = user_titan_amount;
        }

        public String getTarget_price_usdt() {
            return target_price_usdt;
        }

        public void setTarget_price_usdt(String target_price_usdt) {
            this.target_price_usdt = target_price_usdt;
        }

        public String getSource_price_usdt() {
            return source_price_usdt;
        }

        public void setSource_price_usdt(String source_price_usdt) {
            this.source_price_usdt = source_price_usdt;
        }

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
