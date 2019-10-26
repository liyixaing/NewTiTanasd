package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/9.
 */

public class SetHelpRequest {

    /**
     * userkey : d275a41a534b439787f8ea22646cad6b
     * help : eat,user,word
     */

    private String userkey;
    private String help;
    private int language;

    public SetHelpRequest(String userkey, String help) {
        this.userkey = userkey;
        this.help = help;
        this.language = Constant.LANGAGE;
    }
}
