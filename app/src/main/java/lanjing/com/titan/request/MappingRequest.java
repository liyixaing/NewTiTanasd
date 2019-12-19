package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class MappingRequest {
    int sourceCoin;
    int targetCoin;
    int language;

    public MappingRequest(int sourceCoin, int targetCoin) {
        this.sourceCoin = sourceCoin;
        this.targetCoin = targetCoin;
        this.language = Constant.LANGAGE;
    }
}
