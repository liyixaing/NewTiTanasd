package lanjing.com.titan.appupdate;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxh.baselibray.net.ServiceGenerator;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import java.util.HashMap;
import java.util.Map;

public class UpdateHelper {
    Activity mContext;
    boolean isFromHome=false;

    public UpdateHelper(Activity mContex) {
        this.mContext = mContex;
    }

    public UpdateHelper(Activity context, boolean isFromHome) {
        this.mContext = context;
        this.isFromHome = isFromHome;
    }

    public void update(){
        Map<String, String> params=new HashMap<>();
        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(mContext)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateHttpUtil())
                //必须设置，网络请求地址
                .setUpdateUrl(ServiceGenerator.BASE_URL)

                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(true)
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
            //    .hideDialogOnDownloading()
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
            //    .setTopPic(R.mipmap.top_8)
                //为按钮，进度条设置颜色，默认从顶部图片自动识别。
            //    .setThemeColor(ColorUtil.getRandomColor())
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
             //   .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
                //.setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                //不显示通知栏进度条
             //   .dismissNotificationProgress()
                //是否忽略版本
                //.showIgnoreVersion()

                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        Log.d("asd","json="+json);
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                            UpdateResponce data=new Gson().fromJson(json,UpdateResponce.class);
                         //   if (ObjectUtils.isEmpty(updateResponce))return null;
                        updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(Integer.parseInt(data.getData().getVersioncode()) != 107 ?"Yes":"No")
                                    //（必须）新版本号，
                                    .setNewVersion(data.getData().getVersionname())
                                    //（必须）下载地址
                                    .setApkFileUrl(data.getData().getUrl())
                                    //（必须）更新内容
                                    .setUpdateLog(data.getData().getRemarks());
                                    //是否强制更新，可以不设置
//                                    .setConstraint(data.getUpdateStatus()==2?true:false);
                                    //设置md5，可以不设置
                                   // .setNewMd5(jsonObject.optString("new_md51");

                        return updateAppBean;
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    protected void noNewApp(String error) {
                        if (!isFromHome){
                            Toast.makeText(mContext, "没有新版本", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}
