package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/9.
 */

public class SetHelpResponse {

    /**
     * msg : ok
     * help : eat,user,word
     * code : 200
     * keys : d9167c13d99146f0a3d7d5b8a631f768
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
        private String keys;

        public String getHelp() {
            return help;
        }

        public void setHelp(String help) {
            this.help = help;
        }

        public String getKeys() {
            return keys;
        }

        public void setKeys(String keys) {
            this.keys = keys;
        }
    }


}
