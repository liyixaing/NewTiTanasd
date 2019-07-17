package lanjing.com.titan.request;

public class AddFeedbackRequest {
    public String content;
    public String title;
    public String pictures;

    public AddFeedbackRequest(String content, String title, String pictures) {
        this.content = content;
        this.title = title;
        this.pictures = pictures;
    }
}
