package lanjing.com.titan.request;

public class RransferRequest {
    private String coin;
    private String toUser;
    private String amount;
    private String memo;

    public RransferRequest(String coin, String toUser, String amount, String memo) {
        this.coin = coin;
        this.toUser = toUser;
        this.amount = amount;
        this.memo = memo;

    }
}
