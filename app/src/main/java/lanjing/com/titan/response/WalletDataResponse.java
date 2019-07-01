package lanjing.com.titan.response;

/**
 * Created by chenxi on 2019/5/12.
 */

public class WalletDataResponse {


    /**
     * msg : ok
     * address : fafagagsgfnfklvdsfsmg
     * WelletId : 99999928
     * code : 200
     * data : {"USD1coinId":"25","USD1num":"7.0","USD1price":"49.0","USD2coinId":"90","USD2num":"0.0","USD2price":"0.0","TitancoinId":"23","Titannum":"75.23","Titanprice":"135.41400000000002","TitanccoinId":"24","Titancnum":"1.23","Titancprice":"9.347999999999999"}
     * sum : 193.762
     */

    private String msg;
    private String address;
    private String WelletId;
    private int code;
    private DataBean data;
    private String sum;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWelletId() {
        return WelletId;
    }

    public void setWelletId(String WelletId) {
        this.WelletId = WelletId;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public static class DataBean {
        /**
         * USD1coinId : 25
         * USD1num : 7.0
         * USD1price : 49.0
         * USD2coinId : 90
         * USD2num : 0.0
         * USD2price : 0.0
         * TitancoinId : 23
         * Titannum : 75.23
         * Titanprice : 135.41400000000002
         * TitanccoinId : 24
         * Titancnum : 1.23
         * Titancprice : 9.347999999999999
         */

        private String USD1coinId;
        private String USD1num;
        private String USD1price;
        private String USD2coinId;
        private String USD2num;
        private String USD2price;
        private String TitancoinId;
        private String Titannum;
        private String Titanprice;
        private String TitanccoinId;
        private String Titancnum;
        private String Titancprice;
        private String TitanShiftnum;


        public String getTitanShiftnum() {
            return TitanShiftnum;
        }

        public void setTitanShiftnum(String titanShiftnum) {
            TitanShiftnum = titanShiftnum;
        }

        public String getUSD1coinId() {
            return USD1coinId;
        }

        public void setUSD1coinId(String USD1coinId) {
            this.USD1coinId = USD1coinId;
        }

        public String getUSD1num() {
            return USD1num;
        }

        public void setUSD1num(String USD1num) {
            this.USD1num = USD1num;
        }

        public String getUSD1price() {
            return USD1price;
        }

        public void setUSD1price(String USD1price) {
            this.USD1price = USD1price;
        }

        public String getUSD2coinId() {
            return USD2coinId;
        }

        public void setUSD2coinId(String USD2coinId) {
            this.USD2coinId = USD2coinId;
        }

        public String getUSD2num() {
            return USD2num;
        }

        public void setUSD2num(String USD2num) {
            this.USD2num = USD2num;
        }

        public String getUSD2price() {
            return USD2price;
        }

        public void setUSD2price(String USD2price) {
            this.USD2price = USD2price;
        }

        public String getTitancoinId() {
            return TitancoinId;
        }

        public void setTitancoinId(String TitancoinId) {
            this.TitancoinId = TitancoinId;
        }

        public String getTitannum() {
            return Titannum;
        }

        public void setTitannum(String Titannum) {
            this.Titannum = Titannum;
        }

        public String getTitanprice() {
            return Titanprice;
        }

        public void setTitanprice(String Titanprice) {
            this.Titanprice = Titanprice;
        }

        public String getTitanccoinId() {
            return TitanccoinId;
        }

        public void setTitanccoinId(String TitanccoinId) {
            this.TitanccoinId = TitanccoinId;
        }

        public String getTitancnum() {
            return Titancnum;
        }

        public void setTitancnum(String Titancnum) {
            this.Titancnum = Titancnum;
        }

        public String getTitancprice() {
            return Titancprice;
        }

        public void setTitancprice(String Titancprice) {
            this.Titancprice = Titancprice;
        }
    }
}
