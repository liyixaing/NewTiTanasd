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
        private double usdSellGainCoin1Rate;
        private int coin2Price;
        private double gainCoin1Amount;
        private int orderId;
        private int tradeFee;
        private String tradeFeeUnit;
        private String coin2Name;
        private int usdSellGainCoin2;
        private int tradeAmount;
        private int usdSellGainCoin1;
        private String createTime;
        private String tradeAmountUnit;
        private double gainCoin2Amount;
        private int id;
        private double coin1Price;
        private double usdSellGainCoin2Rate;
        private String coin1Name;

        public double getUsdSellGainCoin1Rate() {
            return usdSellGainCoin1Rate;
        }

        public void setUsdSellGainCoin1Rate(double usdSellGainCoin1Rate) {
            this.usdSellGainCoin1Rate = usdSellGainCoin1Rate;
        }

        public int getCoin2Price() {
            return coin2Price;
        }

        public void setCoin2Price(int coin2Price) {
            this.coin2Price = coin2Price;
        }

        public double getGainCoin1Amount() {
            return gainCoin1Amount;
        }

        public void setGainCoin1Amount(double gainCoin1Amount) {
            this.gainCoin1Amount = gainCoin1Amount;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getTradeFee() {
            return tradeFee;
        }

        public void setTradeFee(int tradeFee) {
            this.tradeFee = tradeFee;
        }

        public String getTradeFeeUnit() {
            return tradeFeeUnit;
        }

        public void setTradeFeeUnit(String tradeFeeUnit) {
            this.tradeFeeUnit = tradeFeeUnit;
        }

        public String getCoin2Name() {
            return coin2Name;
        }

        public void setCoin2Name(String coin2Name) {
            this.coin2Name = coin2Name;
        }

        public int getUsdSellGainCoin2() {
            return usdSellGainCoin2;
        }

        public void setUsdSellGainCoin2(int usdSellGainCoin2) {
            this.usdSellGainCoin2 = usdSellGainCoin2;
        }

        public int getTradeAmount() {
            return tradeAmount;
        }

        public void setTradeAmount(int tradeAmount) {
            this.tradeAmount = tradeAmount;
        }

        public int getUsdSellGainCoin1() {
            return usdSellGainCoin1;
        }

        public void setUsdSellGainCoin1(int usdSellGainCoin1) {
            this.usdSellGainCoin1 = usdSellGainCoin1;
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

        public double getGainCoin2Amount() {
            return gainCoin2Amount;
        }

        public void setGainCoin2Amount(double gainCoin2Amount) {
            this.gainCoin2Amount = gainCoin2Amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getCoin1Price() {
            return coin1Price;
        }

        public void setCoin1Price(double coin1Price) {
            this.coin1Price = coin1Price;
        }

        public double getUsdSellGainCoin2Rate() {
            return usdSellGainCoin2Rate;
        }

        public void setUsdSellGainCoin2Rate(double usdSellGainCoin2Rate) {
            this.usdSellGainCoin2Rate = usdSellGainCoin2Rate;
        }

        public String getCoin1Name() {
            return coin1Name;
        }

        public void setCoin1Name(String coin1Name) {
            this.coin1Name = coin1Name;
        }
    }

}
