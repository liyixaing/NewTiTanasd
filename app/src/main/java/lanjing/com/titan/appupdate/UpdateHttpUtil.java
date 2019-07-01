package lanjing.com.titan.appupdate;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.lxh.baselibray.net.ServiceGenerator;
import com.vector.update_app.HttpManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import lanjing.com.titan.response.VersionResponse;
import lanjing.com.titan.util.ThreadPoolUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 使用OkGo实现接口
 */

public class UpdateHttpUtil implements HttpManager {
    private final int CONNECT_TIME_OUT = 10000;
    private final int READ_TIME_OUT = 20000;
    private Handler mHandler = new Handler();
    private float progress;

    public interface ApiUpdateService{
        /**
         * 版本更新
         * @param
         * @param
         * @return
         */
        @POST("/version/findVersion")
        Call<UpdateResponce> updateApp(@Body RequestBody body);

//        @POST("/version/findVersion")
//        Call<UpdateResponce> updateApp(@Header("token") String token);
    }

    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param params   get参数
     * @param callBack 回调
     */
    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {

    }

    /**
     * 异步post
     *
     * @param url      post请求地址
     * @param params   post请求参数
     * @param callBack 回调
     */
    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        ApiUpdateService service = ServiceGenerator.createService(ApiUpdateService.class);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type",1);
            jsonObject.put("version", 107);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        service.updateApp(body).enqueue(new retrofit2.Callback<UpdateResponce>() {
            @Override
            public void onResponse(Call<UpdateResponce> call, Response<UpdateResponce> response) {
                callBack.onResponse(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<UpdateResponce> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
        ThreadPoolUtil.execute(() -> {
            try {
                URL uRl = new URL(url);
                HttpURLConnection con = (HttpURLConnection) uRl.openConnection();
                con.setRequestMethod("GET");
                con.setReadTimeout(READ_TIME_OUT);
                con.setConnectTimeout(CONNECT_TIME_OUT);
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = con.getInputStream();
                    int length = con.getContentLength();
                    int len;
                    //当前已下载完成的进度
                    byte[] buffer = new byte[1024 * 4];
                    File file = new File(path+fileName);
                    FileOutputStream stream = new FileOutputStream(file);
                    mHandler.post(() -> callback.onBefore());
                    while ((len = is.read(buffer)) != -1) {
                        //将获取到的流写入文件中
                        stream.write(buffer, 0, len);
                        progress += len;
                        Log.d("asd","progress="+progress+ "total="+length+"present="+progress/length);
                        mHandler.post(() -> callback.onProgress(progress/length,length));

                    }
                    //完成io操作,释放资源
                    stream.flush();
                    stream.close();
                    is.close();
                    mHandler.post(() -> callback.onResponse(file));

                } else {
                    mHandler.post(() -> callback.onError("连接超时"));

                }
                con.disconnect();
            } catch (Exception e) {
                mHandler.post(() -> callback.onError(e.getMessage()));
                e.printStackTrace();
            }
        });
    }

}