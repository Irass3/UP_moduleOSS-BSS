package com.app.vkrsafonenko.DialogWindow;

public class DialogWindowParam {
    private String title;
    private String messasge;
    private String openAfterClosing;
    public DialogWindowParam(String title, String messasge) {
        this.title = title;
        this.messasge = messasge;
    }

    public DialogWindowParam(String title, String messasge, String openAfterClosing) {
        this.title = title;
        this.messasge = messasge;
        this.openAfterClosing = openAfterClosing;
    }

    public String getTitle() {
        return title;
    }

    public String getMessasge() {
        return messasge;
    }

    public String getOpenAfterClosing() {
        return openAfterClosing;
    }
}
