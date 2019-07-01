package lanjing.com.titan.request;

/**
 * Created by chenxi on 2019/5/25.
 */

public class FeedBackRequest {


    /**
     * content : hello,test23
     * title : 123
     */

    private String content;
    private String title;


    public FeedBackRequest(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
