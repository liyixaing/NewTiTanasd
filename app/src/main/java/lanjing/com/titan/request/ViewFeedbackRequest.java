package lanjing.com.titan.request;

import lanjing.com.titan.constant.Constant;

public class ViewFeedbackRequest {
    public String feedbackId;
    private int language;

    public ViewFeedbackRequest(String feedbackId) {
        this.feedbackId = feedbackId;
        this.language = Constant.LANGAGE;
    }
}
