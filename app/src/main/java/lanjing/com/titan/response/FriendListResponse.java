package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/10.
 */

public class FriendListResponse {

    /**
     * reward : 0
     * msg : ok
     * code : 200
     * data : [{"nickName":"test2","phonenum":"10086","time":"2019-05-10 19:40:33"},{"nickName":"test2","phonenum":"","time":""},{"nickName":"test2","phonenum":"","time":""},{"nickName":"hh","phonenum":"","time":""},{"nickName":"hh","phonenum":"","time":""}]
     * num : 7
     */

    private double reward;
    private String msg;
    private int code;

    public String getRecommendurl() {
        return recommendurl;
    }

    public void setRecommendurl(String recommendurl) {
        this.recommendurl = recommendurl;
    }

    public String getRegisterurl() {
        return registerurl;
    }

    public void setRegisterurl(String registerurl) {
        this.registerurl = registerurl;
    }

    private String recommendurl;
    private String registerurl;
    private int num;
    private int current_predice_mining_number_of_people;
    private Double current_predice_mining_number_of_usd;
    private String lt_region_earnings;
    private String lg_region_earnings;

    public String getLt_region_earnings() {
        return lt_region_earnings;
    }

    public void setLt_region_earnings(String lt_region_earnings) {
        this.lt_region_earnings = lt_region_earnings;
    }

    public String getLg_region_earnings() {
        return lg_region_earnings;
    }

    public void setLg_region_earnings(String lg_region_earnings) {
        this.lg_region_earnings = lg_region_earnings;
    }

    public int getCurrent_predice_mining_number_of_people() {
        return current_predice_mining_number_of_people;
    }

    public void setCurrent_predice_mining_number_of_people(int current_predice_mining_number_of_people) {
        this.current_predice_mining_number_of_people = current_predice_mining_number_of_people;
    }

    public Double getCurrent_predice_mining_number_of_usd() {
        return current_predice_mining_number_of_usd;
    }

    public void setCurrent_predice_mining_number_of_usd(Double current_predice_mining_number_of_usd) {
        this.current_predice_mining_number_of_usd = current_predice_mining_number_of_usd;
    }

    private List<DataBean> data;

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public String getfId() {
            return fId;
        }

        public void setfId(String fId) {
            this.fId = fId;
        }

        /**
         * fId
         * nickName : test2
         * phonenum : 10086
         * time : 2019-05-10 19:40:33
         */
        private String fId;
        private String nickName;
        private String phonenum;
        private String time;
        private String predice_mining_number_of_usd;
        private int valid;

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }

        public String getPredice_mining_number_of_usd() {
            return predice_mining_number_of_usd;
        }

        public void setPredice_mining_number_of_usd(String predice_mining_number_of_usd) {
            this.predice_mining_number_of_usd = predice_mining_number_of_usd;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
