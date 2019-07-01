package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/26.
 */

public class VersionResponse {

    /**
     * code : 200
     * msg : success
     * data : [{"id":1,"name":null,"remarks":"android 下载地址","url":"http://www.baidu.com","versionname":"110","versioncode":"1.0.0"},{"id":2,"name":null,"remarks":"ios下载地址","url":"http://www.baidu.com","versionname":"110","versioncode":"1.0.0"}]
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
         * name : null
         * remarks : android 下载地址
         * url : http://www.baidu.com
         * versionname : 110
         * versioncode : 1.0.0
         */

        private int id;
        private Object name;
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

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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
