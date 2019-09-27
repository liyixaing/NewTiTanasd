package lanjing.com.titan.response;

public class SeckillCdkeyResponse {
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
        private int freeTimes;
        private int isSuccessSeckill;
        private String newestSeckillCdkey;
        private int startTime;
        private int isStart;
        private int isSeckillFinish;
        private int isSeckillEnd;

        public int getFreeTimes() {
            return freeTimes;
        }

        public void setFreeTimes(int freeTimes) {
            this.freeTimes = freeTimes;
        }

        public int getIsSuccessSeckill() {
            return isSuccessSeckill;
        }

        public void setIsSuccessSeckill(int isSuccessSeckill) {
            this.isSuccessSeckill = isSuccessSeckill;
        }

        public String getNewestSeckillCdkey() {
            return newestSeckillCdkey;
        }

        public void setNewestSeckillCdkey(String newestSeckillCdkey) {
            this.newestSeckillCdkey = newestSeckillCdkey;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getIsStart() {
            return isStart;
        }

        public void setIsStart(int isStart) {
            this.isStart = isStart;
        }

        public int getIsSeckillFinish() {
            return isSeckillFinish;
        }

        public void setIsSeckillFinish(int isSeckillFinish) {
            this.isSeckillFinish = isSeckillFinish;
        }

        public int getIsSeckillEnd() {
            return isSeckillEnd;
        }

        public void setIsSeckillEnd(int isSeckillEnd) {
            this.isSeckillEnd = isSeckillEnd;
        }
    }
}
