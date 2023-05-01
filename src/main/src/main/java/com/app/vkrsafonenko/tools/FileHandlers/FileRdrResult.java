package com.app.vkrsafonenko.tools.FileHandlers;

public class FileRdrResult {
    private boolean fileIsEmpty;
    private boolean fileExist;
    private String Text;

    public void setFileIsEmpty(boolean fileIsEmpty) {
        this.fileIsEmpty = fileIsEmpty;
    }

    public boolean isFileIsEmpty() {
        return fileIsEmpty;
    }

    public boolean isFileExist() {
        return fileExist;
    }

    public void setFileExist(boolean fileExist) {
        this.fileExist = fileExist;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
