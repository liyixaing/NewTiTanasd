package lanjing.com.titan.request;

/**
 * Created by PCyzh on 2019/6/20.
 */

public class VersionRequest {

    int type;
    int version;

    public VersionRequest(int type, int version) {
        this.type = type;
        this.version = version;
    }
}
