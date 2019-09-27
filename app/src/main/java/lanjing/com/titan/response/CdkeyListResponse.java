package lanjing.com.titan.response;

import java.util.List;

public class CdkeyListResponse {
    private String msg;
    private int code;
    public List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String cdkey;//激活码
        private int createTime;//生成时间
        private int id;//主键
        private String ownUser;//拥有人
        private int status;//状态，0：未使用 1：已使用
        private int useTime;//已使用
        private String useUser;//使用人

        public String getCdkey() {
            return cdkey;
        }

        public void setCdkey(String cdkey) {
            this.cdkey = cdkey;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOwnUser() {
            return ownUser;
        }

        public void setOwnUser(String ownUser) {
            this.ownUser = ownUser;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUseTime() {
            return useTime;
        }

        public void setUseTime(int useTime) {
            this.useTime = useTime;
        }

        public String getUseUser() {
            return useUser;
        }

        public void setUseUser(String useUser) {
            this.useUser = useUser;
        }
    }
}
