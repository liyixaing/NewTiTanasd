package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class ConvertRequest {
    private String sourceCoin;
    private String sourceAmount;
    private String targetCoin;
    private int language;

    public ConvertRequest(String sourceCoin, String sourceAmount, String targetCoin) {
        this.sourceCoin = sourceCoin;
        this.sourceAmount = sourceAmount;
        this.targetCoin = targetCoin;
        this.language = Constant.LANGAGE;
    }
}
