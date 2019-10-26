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
    private int code;
    public Data data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String help;

        public String getHelp() {
            return help;
        }

        public void setHelp(String help) {
            this.help = help;
        }
    }


}
