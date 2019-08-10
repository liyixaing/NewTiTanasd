package lanjing.com.titan.response;

public class SellOrderDetailResponse {

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
        private double barPrice;
        private int orderId;
        private double tradeFee;
        private String tradeFeeUnit;
        private double ttPrice;
        private double usdSellGainBarRate;
        private double gainTtAmount;
        private double usdSellGainTtRate;
        private double tradeAmount;
        private double gainBarAmount;
        private String createTime;
        private String tradeAmountUnit;
        private int id;

        public double getBarPrice() {
            return barPrice;
        }

        public void setBarPrice(double barPrice) {
            this.barPrice = barPrice;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public double getTradeFee() {
            return tradeFee;
        }

        public void setTradeFee(double tradeFee) {
            this.tradeFee = tradeFee;
        }

        public String getTradeFeeUnit() {
            return tradeFeeUnit;
        }

        public void setTradeFeeUnit(String tradeFeeUnit) {
            this.tradeFeeUnit = tradeFeeUnit;
        }

        public double getTtPrice() {
            return ttPrice;
        }

        public void setTtPrice(double ttPrice) {
            this.ttPrice = ttPrice;
        }

        public double getUsdSellGainBarRate() {
            return usdSellGainBarRate;
        }

        public void setUsdSellGainBarRate(double usdSellGainBarRate) {
            this.usdSellGainBarRate = usdSellGainBarRate;
        }

        public double getGainTtAmount() {
            return gainTtAmount;
        }

        public void setGainTtAmount(double gainTtAmount) {
            this.gainTtAmount = gainTtAmount;
        }

        public double getUsdSellGainTtRate() {
            return usdSellGainTtRate;
        }

        public void setUsdSellGainTtRate(double usdSellGainTtRate) {
            this.usdSellGainTtRate = usdSellGainTtRate;
        }

        public double getTradeAmount() {
            return tradeAmount;
        }

        public void setTradeAmount(double tradeAmount) {
            this.tradeAmount = tradeAmount;
        }

        public double getGainBarAmount() {
            return gainBarAmount;
        }

        public void setGainBarAmount(double gainBarAmount) {
            this.gainBarAmount = gainBarAmount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTradeAmountUnit() {
            return tradeAmountUnit;
        }

        public void setTradeAmountUnit(String tradeAmountUnit) {
            this.tradeAmountUnit = tradeAmountUnit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
