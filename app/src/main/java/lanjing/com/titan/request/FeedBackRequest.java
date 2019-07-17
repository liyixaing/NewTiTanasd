package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/25.
 */

public class FeedBackRequest {


    /**
     * content : hello,test23
     * title : 123
     */

    public String content;
    public String title;
    public String pictures;


    public FeedBackRequest(String content, String title, String pictures) {
        this.content = content;
        this.title = title;
        this.pictures = pictures;
    }
}
