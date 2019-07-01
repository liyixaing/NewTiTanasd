package lanjing.com.titan.appupdate;

public class UpdateResponce {


    /**
     * code : 202
     * msg : success
     * data : {"id":1,"name":"android","remarks":"android 下载地址","url":"http://www.baidu.com","versionname":"1.0.0","versioncode":"110"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : android
         * remarks : android 下载地址
         * url : http://www.baidu.com
         * versionname : 1.0.0
         * versioncode : 110
         */

        private int id;
        private String name;
        private String remarks;
        private String url;
        private String versionname;
        private String versioncode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersionname() {
            return versionname;
        }

        public void setVersionname(String versionname) {
            this.versionname = versionname;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(String versioncode) {
            this.versioncode = versioncode;
        }
    }
}
