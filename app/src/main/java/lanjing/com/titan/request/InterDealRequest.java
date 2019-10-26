package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/20.
 */

public class InterDealRequest {

    /**
     * IsAuto : 1
     */

    private int IsAuto;
    private int language;

    public InterDealRequest(int isAuto) {
        IsAuto = isAuto;
        this.language = Constant.LANGAGE;
    }
}
