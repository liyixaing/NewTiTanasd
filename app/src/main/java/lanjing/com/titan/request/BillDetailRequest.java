package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/13.
 * 获取钱包详情数据  请求实体
 */

public class BillDetailRequest {

    /**
     * historyId : 2
     */

    private String logId;


    public BillDetailRequest(String logId) {
        this.logId = logId;
    }
}
