package lanjing.com.titan.eventbus;

import com.lxh.baselibray.event.IBus;

public class EventImpl {
    /**
     * 修改头像事件
     */
    public static class UpdatePortraitEvent implements IBus.IEvent{

        @Override
        public int getTag() {
            return 0;
        }
    }

    /**
     * 修改昵称
     */
    public static class UpdateNicknameEvent implements IBus.IEvent{

        @Override
        public int getTag() {
            return 1;
        }
    }

    /**
     * 修改手机号
     */
    public static class UpdatePhoneEvent implements IBus.IEvent{

        @Override
        public int getTag() {
            return 2;
        }
    }

    /**
     * 修改钱包名称
     */
    public static class UpdateWalletNameEvent implements IBus.IEvent{

        @Override
        public int getTag() {
            return 3;
        }
    }

    /**
     * 实名认证
     */
    public static class RealNameEvent implements IBus.IEvent{

        @Override
        public int getTag() {
            return 3;
        }
    }

    /**
     * 隐私模式开启  关闭
     */
    public static class Amounthide implements IBus.IEvent{

        private int type;

        public Amounthide(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }


        @Override
        public int getTag() {

            return 4;
        }
    }

//
//    /**
//     * 退款  取消订单事件
//     */
//    public static class SureOrderEvent implements IBus.IEvent{
//
//        @Override
//        public int getTag() {
//            return 3;
//        }
//    }
}
