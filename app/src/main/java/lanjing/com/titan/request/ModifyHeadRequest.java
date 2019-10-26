package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/10.
 */

public class ModifyHeadRequest {


    /**
     * picture : fdfsafda
     */

    private String picture;
    private String type;
    private int language;

    public ModifyHeadRequest(String picture, String type) {
        this.picture = picture;
        this.type = type;
        this.language = Constant.LANGAGE;
    }
}
