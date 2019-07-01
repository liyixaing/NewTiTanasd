package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/14.
 */

public class WalletListResponse {


     /**
     * msg : ok
     * code : 200
     * data : [{"wId":"99999906","address":"fafagagsgfnfklvdsfsmg","welletName":"123456??","username":"Asd","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTgwNjYwNjY1MzEsInBheWxvYWQiOiJ7XCJkZXZpY2VcIjpcInRlc3QxXCIsXCJrZXllc1wiOlwiOTk5OTk5MDZcIn0ifQ.6b4ZvpX5FBLcaxKZ6T653RIWvxbwKCJN5xH0uf6FJcA"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * wId : 99999906
         * address : fafagagsgfnfklvdsfsmg
         * welletName : 123456??
         * username : Asd
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTgwNjYwNjY1MzEsInBheWxvYWQiOiJ7XCJkZXZpY2VcIjpcInRlc3QxXCIsXCJrZXllc1wiOlwiOTk5OTk5MDZcIn0ifQ.6b4ZvpX5FBLcaxKZ6T653RIWvxbwKCJN5xH0uf6FJcA
         */

        private String wId;
        private String address;
        private String welletName;
        private String username;
        private String token;

        public String getWId() {
            return wId;
        }

        public void setWId(String wId) {
            this.wId = wId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getWelletName() {
            return welletName;
        }

        public void setWelletName(String welletName) {
            this.welletName = welletName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
