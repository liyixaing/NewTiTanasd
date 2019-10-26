package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by PCyzh on 2019/6/20.
 */

public class VersionRequest {

    int type;
    int version;
    private int language;

    public VersionRequest(int type, int version) {
        this.type = type;
        this.version = version;
        this.language = Constant.LANGAGE;
    }
}
