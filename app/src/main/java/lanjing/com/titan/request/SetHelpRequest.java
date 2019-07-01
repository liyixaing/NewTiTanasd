package lanjing.com.titan.request;

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


    public SetHelpRequest(String userkey, String help) {
        this.userkey = userkey;
        this.help = help;
    }
}
