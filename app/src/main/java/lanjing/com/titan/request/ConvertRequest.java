package lanjing.com.titan.request;

public class ConvertRequest {
    private String sourceCoin;
    private String sourceAmount;
    private String targetCoin;

    public ConvertRequest(String sourceCoin, String sourceAmount, String targetCoin) {
        this.sourceCoin = sourceCoin;
        this.sourceAmount = sourceAmount;
        this.targetCoin = targetCoin;

    }
}
