package lanjing.com.titan.response;

public class MappingResponse {
    private int code;
    private String msg;
    public Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String mapping_switch;
        private double mapping_rate;
        private double mapping_fee_rate;
        private double target_price_usdt;
        private double source_coin_amount;
        private double source_price_usdt;

        public String getMapping_switch() {
            return mapping_switch;
        }

        public void setMapping_switch(String mapping_switch) {
            this.mapping_switch = mapping_switch;
        }

        public double getMapping_rate() {
            return mapping_rate;
        }

        public void setMapping_rate(double mapping_rate) {
            this.mapping_rate = mapping_rate;
        }

        public double getMapping_fee_rate() {
            return mapping_fee_rate;
        }

        public void setMapping_fee_rate(double mapping_fee_rate) {
            this.mapping_fee_rate = mapping_fee_rate;
        }

        public double getTarget_price_usdt() {
            return target_price_usdt;
        }

        public void setTarget_price_usdt(double target_price_usdt) {
            this.target_price_usdt = target_price_usdt;
        }

        public double getSource_coin_amount() {
            return source_coin_amount;
        }

        public void setSource_coin_amount(double source_coin_amount) {
            this.source_coin_amount = source_coin_amount;
        }

        public double getSource_price_usdt() {
            return source_price_usdt;
        }

        public void setSource_price_usdt(double source_price_usdt) {
            this.source_price_usdt = source_price_usdt;
        }
    }
}
