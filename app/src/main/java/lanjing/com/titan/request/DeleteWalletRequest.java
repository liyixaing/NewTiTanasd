package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/15.
 */

public class DeleteWalletRequest {

    /**
     * token : sfasfjdkalfdkladf
     */

    private String tokens;
    private int language;

    public DeleteWalletRequest(String tokens) {
        this.tokens = tokens;
        this.language = Constant.LANGAGE;
    }
}
