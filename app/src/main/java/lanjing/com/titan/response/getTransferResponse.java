package lanjing.com.titan.response;

public class getTransferResponse {


    /*{
    "msg": "操作成功！",
    "code": 200,
    "data": {
        "createTime": 1562832034,
        "id": 1,
        "state": 1,
        "toAccount": "xxx",
        "toMemo": "222",
        "toTag": "aaa",
        "updateTime": 1562832169,
        "userKey": "2012222"
    }
}*/

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
        private String createTime;
        private String id;
        private String state;
        private String toAccount;
        private String toMemo;
        private String toTag;
        private String updateTime;
        private String userKey;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getToAccount() {
            return toAccount;
        }

        public void setToAccount(String toAccount) {
            this.toAccount = toAccount;
        }

        public String getToMemo() {
            return toMemo;
        }

        public void setToMemo(String toMemo) {
            this.toMemo = toMemo;
        }

        public String getToTag() {
            return toTag;
        }

        public void setToTag(String toTag) {
            this.toTag = toTag;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }
    }
}
