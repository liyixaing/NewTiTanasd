package lanjing.com.titan.contact;


import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

public class MappingContact {
    public static class MappingPresent extends BasePresent<IWalletDetailView> {

    }

    public interface IWalletDetailView extends IBaseView {
        void getDataFailed();
    }
}
