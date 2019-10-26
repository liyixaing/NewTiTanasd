package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

/**
 * Created by chenxi on 2019/5/10.
 */

public class ModifyNicknameRequest {

    /**
     * nickname : test
     */

    private String nickname;
    private int language;

    public ModifyNicknameRequest(String nickname) {
        this.nickname = nickname;
        this.language = Constant.LANGAGE;
    }
}
