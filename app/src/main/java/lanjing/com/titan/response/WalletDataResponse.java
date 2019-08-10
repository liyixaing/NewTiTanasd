package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/12.
 */

public class WalletDataResponse {


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


        private String total_asset_usd;
        private String user_tag;
        public List<Wellets> wellets;
        private String wait_view_feedback_count;
        private String user_address;
        private String total_asset_cny;

        public String getTotal_asset_usd() {
            return total_asset_usd;
        }

        public void setTotal_asset_usd(String total_asset_usd) {
            this.total_asset_usd = total_asset_usd;
        }

        public String getUser_tag() {
            return user_tag;
        }

        public void setUser_tag(String user_tag) {
            this.user_tag = user_tag;
        }

        public List<Wellets> getWellets() {
            return wellets;
        }

        public void setWellets(List<Wellets> wellets) {
            this.wellets = wellets;
        }

        public String getWait_view_feedback_count() {
            return wait_view_feedback_count;
        }

        public void setWait_view_feedback_count(String wait_view_feedback_count) {
            this.wait_view_feedback_count = wait_view_feedback_count;
        }

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }

        public String getTotal_asset_cny() {
            return total_asset_cny;
        }

        public void setTotal_asset_cny(String total_asset_cny) {
            this.total_asset_cny = total_asset_cny;
        }

        public static class Wellets {
            private String coin_cny_worth;
            private String coin_usd_worth;
            private String coin_num;
            private String coin;

            public String getCoin_cny_worth() {
                return coin_cny_worth;
            }

            public void setCoin_cny_worth(String coin_cny_worth) {
                this.coin_cny_worth = coin_cny_worth;
            }

            public String getCoin_usd_worth() {
                return coin_usd_worth;
            }

            public void setCoin_usd_worth(String coin_usd_worth) {
                this.coin_usd_worth = coin_usd_worth;
            }

            public String getCoin_num() {
                return coin_num;
            }

            public void setCoin_num(String coin_num) {
                this.coin_num = coin_num;
            }

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }
        }


    }
}
