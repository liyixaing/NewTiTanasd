package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/10.
 */

public class FriendListResponse {


    private int code;
    private String msg;
    public Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String reward;
        public List<ChildList> child_list;

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public static class ChildList {
            private String fId;
            private int valid;
            private String nickName;
            private String predice_mining_number_of_usd;
            private String phonenum;
            private String time;

            public String getfId() {
                return fId;
            }

            public void setfId(String fId) {
                this.fId = fId;
            }

            public int getValid() {
                return valid;
            }

            public void setValid(int valid) {
                this.valid = valid;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPredice_mining_number_of_usd() {
                return predice_mining_number_of_usd;
            }

            public void setPredice_mining_number_of_usd(String predice_mining_number_of_usd) {
                this.predice_mining_number_of_usd = predice_mining_number_of_usd;
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

        private String current_predice_mining_number_of_people;

        public String getCurrent_predice_mining_number_of_people() {
            return current_predice_mining_number_of_people;
        }

        public void setCurrent_predice_mining_number_of_people(String current_predice_mining_number_of_people) {
            this.current_predice_mining_number_of_people = current_predice_mining_number_of_people;
        }

        private String recommendurl;
        private String registerurl;
        private String lt_region_earnings;
        private int num;
        private String lg_region_earnings;
        private String current_predice_mining_number_of_usd;



        public List<ChildList> getChild_list() {
            return child_list;
        }

        public void setChild_list(List<ChildList> child_list) {
            this.child_list = child_list;
        }



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

        public String getLt_region_earnings() {
            return lt_region_earnings;
        }

        public void setLt_region_earnings(String lt_region_earnings) {
            this.lt_region_earnings = lt_region_earnings;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getLg_region_earnings() {
            return lg_region_earnings;
        }

        public void setLg_region_earnings(String lg_region_earnings) {
            this.lg_region_earnings = lg_region_earnings;
        }

        public String getCurrent_predice_mining_number_of_usd() {
            return current_predice_mining_number_of_usd;
        }

        public void setCurrent_predice_mining_number_of_usd(String current_predice_mining_number_of_usd) {
            this.current_predice_mining_number_of_usd = current_predice_mining_number_of_usd;
        }
    }


}
