package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class RequestConvertConfig {

    private int sourceCoin;
    private int targetCoin;
    private int language;

    public RequestConvertConfig(int sourceCoin, int targetCoin) {
        this.sourceCoin = sourceCoin;
        this.targetCoin = targetCoin;
        this.language = Constant.LANGAGE;
    }
}
