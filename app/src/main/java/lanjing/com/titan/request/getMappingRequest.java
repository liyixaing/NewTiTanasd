package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class getMappingRequest {
    private int sourceCoin;
    private String sourceAmount;
    private int targetCoin;
    private int language;

    public getMappingRequest(int sourceCoin, String sourceAmount, int targetCoin) {
        this.sourceCoin = sourceCoin;
        this.sourceAmount = sourceAmount;
        this.targetCoin = targetCoin;
        this.language = Constant.LANGAGE;
    }
}
