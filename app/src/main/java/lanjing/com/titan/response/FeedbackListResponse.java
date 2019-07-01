package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/25.
 */

public class FeedbackListResponse {

    /**
     * msg : ok
     * code : 200
     * data : [{"fid":1,"createtime":"2019-05-25 04:26:10","state":0,"content":"hello,test23","userkey":"99999830","username":"test23"}]
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
         * fid : 1
         * createtime : 2019-05-25 04:26:10
         * state : 0
         * content : hello,test23
         * userkey : 99999830
         * username : test23
         * reply : sdasd
         */

        private int fid;
        private String createtime;
        private int state;
        private String title;
        private String content;
        private String userkey;
        private String username;

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        private String reply;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUserkey() {
            return userkey;
        }

        public void setUserkey(String userkey) {
            this.userkey = userkey;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
