package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/10.
 */

public class PersonResponse {


    /**
     * msg : ok
     * code : 200
     * data : {"createtime":1558878815000,"device":"test1","fid":2012222,"isNodetime":1559710726000,"isVip":0,"isauto":0,"isnode":2,"keyes":"2012222","loginpassword":"63CF31B3385AF8822FB4C44F7D5C0554","nickname":"你好","nodenum":1212.627171,"phonenum":"1234","picture":"","state":1,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjAxNjI1NjYzNzcsInBheWxvYWQiOiJ7XCJkZXZpY2VcIjpcInRlc3QxXCIsXCJrZXllc1wiOlwiMjAxMjIyMlwifSJ9.2-KLJQ7nnIz2e-oKP8wwy00eN9DjrKhg-pMxr-uzHrw","transpassword":"F59BD65F7EDAFB087A81D4DCA06C4910","username":"Lanjing123","welletname":""}
     * Usd : 3205.9389597592
     * grede : 7
     * Titan : 13313.699999
     */

    private String msg;
    private int code;
    private DataBean data;
    private double Usd;
    private int grede;
    private double Titan;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public double getUsd() {
        return Usd;
    }

    public void setUsd(double Usd) {
        this.Usd = Usd;
    }

    public int getGrede() {
        return grede;
    }

    public void setGrede(int grede) {
        this.grede = grede;
    }

    public double getTitan() {
        return Titan;
    }

    public void setTitan(double Titan) {
        this.Titan = Titan;
    }

    public static class DataBean {
        /**
         * createtime : 1558878815000
         * device : test1
         * fid : 2012222
         * isNodetime : 1559710726000
         * isVip : 0
         * isauto : 0
         * isnode : 2
         * keyes : 2012222
         * loginpassword : 63CF31B3385AF8822FB4C44F7D5C0554
         * nickname : 你好
         * nodenum : 1212.627171
         * phonenum : 1234
         * picture :
         * state : 1
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjAxNjI1NjYzNzcsInBheWxvYWQiOiJ7XCJkZXZpY2VcIjpcInRlc3QxXCIsXCJrZXllc1wiOlwiMjAxMjIyMlwifSJ9.2-KLJQ7nnIz2e-oKP8wwy00eN9DjrKhg-pMxr-uzHrw
         * transpassword : F59BD65F7EDAFB087A81D4DCA06C4910
         * username : Lanjing123
         * welletname :
         */

        private long createtime;
        private String device;
        private int fid;
        private long isNodetime;
        private int isVip;
        private int isauto;
        private int isnode;
        private String keyes;
        private String loginpassword;
        private String nickname;
        private double nodenum;
        private String phonenum;
        private String picture;
        private int state;
        private String token;
        private String transpassword;
        private String username;
        private String welletname;

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public long getIsNodetime() {
            return isNodetime;
        }

        public void setIsNodetime(long isNodetime) {
            this.isNodetime = isNodetime;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public int getIsauto() {
            return isauto;
        }

        public void setIsauto(int isauto) {
            this.isauto = isauto;
        }

        public int getIsnode() {
            return isnode;
        }

        public void setIsnode(int isnode) {
            this.isnode = isnode;
        }

        public String getKeyes() {
            return keyes;
        }

        public void setKeyes(String keyes) {
            this.keyes = keyes;
        }

        public String getLoginpassword() {
            return loginpassword;
        }

        public void setLoginpassword(String loginpassword) {
            this.loginpassword = loginpassword;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public double getNodenum() {
            return nodenum;
        }

        public void setNodenum(double nodenum) {
            this.nodenum = nodenum;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTranspassword() {
            return transpassword;
        }

        public void setTranspassword(String transpassword) {
            this.transpassword = transpassword;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getWelletname() {
            return welletname;
        }

        public void setWelletname(String welletname) {
            this.welletname = welletname;
        }
    }
}
