package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/14.
 */

public class SaveWalletRequest {

    /**
     * welletname : test
     */

    private String welletname;
    private int language;

    public SaveWalletRequest(String welletname) {
        this.welletname = welletname;
        this.language = Constant.LANGAGE;
    }
}
