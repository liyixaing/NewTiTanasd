package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/10.
 * 绑定联系人  请求实体
 */

public class BindContactsRequest {

    /**
     * Invitacode : 1
     */

    private String Invitacode;


    public BindContactsRequest(String invitacode) {
        Invitacode = invitacode;
    }
}
