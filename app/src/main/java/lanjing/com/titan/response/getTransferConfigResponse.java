package lanjing.com.titan.response;

public class getTransferConfigResponse {
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
        private String TAITAN_TRANSFER_MAX_VALUE;
        private String COIN_IS_SUPPORT;
        private String TAITAN_TRANSFER_SWITCH;
        private String TODAY_TRANSFER_AMOUNT;
        private String SURPLUS_TRANSFER_AMOUNT;
        private String TAITAN_TRANSFER_MIN_VALUE;
        private double TAITAN_TRANSFER_FEE_RATE;

        public String getTAITAN_TRANSFER_MAX_VALUE() {
            return TAITAN_TRANSFER_MAX_VALUE;
        }

        public void setTAITAN_TRANSFER_MAX_VALUE(String TAITAN_TRANSFER_MAX_VALUE) {
            this.TAITAN_TRANSFER_MAX_VALUE = TAITAN_TRANSFER_MAX_VALUE;
        }

        public String getCOIN_IS_SUPPORT() {
            return COIN_IS_SUPPORT;
        }

        public void setCOIN_IS_SUPPORT(String COIN_IS_SUPPORT) {
            this.COIN_IS_SUPPORT = COIN_IS_SUPPORT;
        }

        public String getTAITAN_TRANSFER_SWITCH() {
            return TAITAN_TRANSFER_SWITCH;
        }

        public void setTAITAN_TRANSFER_SWITCH(String TAITAN_TRANSFER_SWITCH) {
            this.TAITAN_TRANSFER_SWITCH = TAITAN_TRANSFER_SWITCH;
        }

        public String getTODAY_TRANSFER_AMOUNT() {
            return TODAY_TRANSFER_AMOUNT;
        }

        public void setTODAY_TRANSFER_AMOUNT(String TODAY_TRANSFER_AMOUNT) {
            this.TODAY_TRANSFER_AMOUNT = TODAY_TRANSFER_AMOUNT;
        }

        public String getSURPLUS_TRANSFER_AMOUNT() {
            return SURPLUS_TRANSFER_AMOUNT;
        }

        public void setSURPLUS_TRANSFER_AMOUNT(String SURPLUS_TRANSFER_AMOUNT) {
            this.SURPLUS_TRANSFER_AMOUNT = SURPLUS_TRANSFER_AMOUNT;
        }

        public String getTAITAN_TRANSFER_MIN_VALUE() {
            return TAITAN_TRANSFER_MIN_VALUE;
        }

        public void setTAITAN_TRANSFER_MIN_VALUE(String TAITAN_TRANSFER_MIN_VALUE) {
            this.TAITAN_TRANSFER_MIN_VALUE = TAITAN_TRANSFER_MIN_VALUE;
        }

        public double getTAITAN_TRANSFER_FEE_RATE() {
            return TAITAN_TRANSFER_FEE_RATE;
        }

        public void setTAITAN_TRANSFER_FEE_RATE(double TAITAN_TRANSFER_FEE_RATE) {
            this.TAITAN_TRANSFER_FEE_RATE = TAITAN_TRANSFER_FEE_RATE;
        }
    }

}
