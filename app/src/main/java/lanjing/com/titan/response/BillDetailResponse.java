package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/13.
 * 获取钱包详情数据  返回实体
 */

public class BillDetailResponse {


    /**
     * msg : ok
     * code : 200
     * history : {"keys":"fdsf","num":"1.630000","state":"已完成","time":"2019-05-13 04:06:25","type":"交易释放"}
     */

    private String msg;
    private int code;
    private Data data;

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
        private String coinDesc;
        private String fromTag;
        private String keys;
        private String changeType;
        private String changeDesc;
        private String num;
        private String toTag;
        private String type;
        private String toAddress;
        private String fromAddress;
        private String state;
        private String time;
        private String coin;

        private String convertRate;
        private String createTime;
        private String sourceCoin;
        private String sourceCoinName;
        private String targetAmount;
        private String sourceAmount;
        private String targetCoin;
        private String targetCoinName;

        public String getCoinDesc() {
            return coinDesc;
        }

        public void setCoinDesc(String coinDesc) {
            this.coinDesc = coinDesc;
        }

        public String getFromTag() {
            return fromTag;
        }

        public void setFromTag(String fromTag) {
            this.fromTag = fromTag;
        }

        public String getKeys() {
            return keys;
        }

        public void setKeys(String keys) {
            this.keys = keys;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public String getChangeDesc() {
            return changeDesc;
        }

        public void setChangeDesc(String changeDesc) {
            this.changeDesc = changeDesc;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getToTag() {
            return toTag;
        }

        public void setToTag(String toTag) {
            this.toTag = toTag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getConvertRate() {
            return convertRate;
        }

        public void setConvertRate(String convertRate) {
            this.convertRate = convertRate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSourceCoin() {
            return sourceCoin;
        }

        public void setSourceCoin(String sourceCoin) {
            this.sourceCoin = sourceCoin;
        }

        public String getSourceCoinName() {
            return sourceCoinName;
        }

        public void setSourceCoinName(String sourceCoinName) {
            this.sourceCoinName = sourceCoinName;
        }

        public String getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(String targetAmount) {
            this.targetAmount = targetAmount;
        }

        public String getSourceAmount() {
            return sourceAmount;
        }

        public void setSourceAmount(String sourceAmount) {
            this.sourceAmount = sourceAmount;
        }

        public String getTargetCoin() {
            return targetCoin;
        }

        public void setTargetCoin(String targetCoin) {
            this.targetCoin = targetCoin;
        }

        public String getTargetCoinName() {
            return targetCoinName;
        }

        public void setTargetCoinName(String targetCoinName) {
            this.targetCoinName = targetCoinName;
        }
    }
}
