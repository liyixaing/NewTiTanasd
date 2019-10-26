package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class AddFeedbackRequest {
    public String content;
    public String title;
    public String pictures;
    private int language;

    public AddFeedbackRequest(String content, String title, String pictures) {
        this.content = content;
        this.title = title;
        this.pictures = pictures;
        this.language = Constant.LANGAGE;
    }
}
