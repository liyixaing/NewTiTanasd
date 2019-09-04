package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/10.
 */

public class PersonResponse {

    private String msg;
    private int code;
    private DataBean data;

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

    public static class DataBean {

        private long createtime;
        private double worthUsd;
        private long isnodetime;
        private double titanNum;
        private double nodenum;
        private String shareNum;
        private String picture;
        private int isvip;
        private String token;
        private String ways;
        private String phone;
        private String nodeadmin;
        private int  isauto;
        private int grade;
        private String beginnodenum;
        private String nickname;
        private int  isnode;
        private String welletname;
        private String inviter;
        private int  id;
        private int state;
        private String device;
        private String keyes;
        private String username;

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public double getWorthUsd() {
            return worthUsd;
        }

        public void setWorthUsd(double worthUsd) {
            this.worthUsd = worthUsd;
        }

        public long getIsnodetime() {
            return isnodetime;
        }

        public void setIsnodetime(long isnodetime) {
            this.isnodetime = isnodetime;
        }

        public double getTitanNum() {
            return titanNum;
        }

        public void setTitanNum(double titanNum) {
            this.titanNum = titanNum;
        }

        public double getNodenum() {
            return nodenum;
        }

        public void setNodenum(double nodenum) {
            this.nodenum = nodenum;
        }

        public String getShareNum() {
            return shareNum;
        }

        public void setShareNum(String shareNum) {
            this.shareNum = shareNum;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getIsvip() {
            return isvip;
        }

        public void setIsvip(int isvip) {
            this.isvip = isvip;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getWays() {
            return ways;
        }

        public void setWays(String ways) {
            this.ways = ways;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNodeadmin() {
            return nodeadmin;
        }

        public void setNodeadmin(String nodeadmin) {
            this.nodeadmin = nodeadmin;
        }

        public int getIsauto() {
            return isauto;
        }

        public void setIsauto(int isauto) {
            this.isauto = isauto;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getBeginnodenum() {
            return beginnodenum;
        }

        public void setBeginnodenum(String beginnodenum) {
            this.beginnodenum = beginnodenum;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getIsnode() {
            return isnode;
        }

        public void setIsnode(int isnode) {
            this.isnode = isnode;
        }

        public String getWelletname() {
            return welletname;
        }

        public void setWelletname(String welletname) {
            this.welletname = welletname;
        }

        public String getInviter() {
            return inviter;
        }

        public void setInviter(String inviter) {
            this.inviter = inviter;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getKeyes() {
            return keyes;
        }

        public void setKeyes(String keyes) {
            this.keyes = keyes;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
