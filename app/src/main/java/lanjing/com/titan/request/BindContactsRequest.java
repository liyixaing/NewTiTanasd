package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/10.
 * 绑定联系人  请求实体
 */

public class BindContactsRequest {

    /**
     * Invitacode : 1
     */

    private String Invitacode;
    private int language;


    public BindContactsRequest(String invitacode) {
        Invitacode = invitacode;
        this.language = Constant.LANGAGE;
    }
}
