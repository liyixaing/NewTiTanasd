package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/12.
 */

public class DealPwdHelpResponse {

    /**
     * msg : ok
     * help : fjsfkd，fdsfds，fsh
     * code : 200
     */

    private String msg;
    private String help;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
