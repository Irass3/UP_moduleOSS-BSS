package com.app.vkrsafonenko.DialogWindow;


import java.io.File;

import com.app.vkrsafonenko.tools.FileHandlers.FileRdr;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DialogWindowController {
    @FXML
    private Text Message;
    @FXML
    void initialize() {
        File targetFile = new File("DialogWindowParam.conf");
        FileRdr reader = new FileRdr("DialogWindowParam.conf", false);
        String[] arr = reader.getResult().getText().split("//");
        Message.setText(arr[1]);
        targetFile.delete();
    }
    public void onStageClose() {
        System.out.println("Ура! расходимся! =)");
    }
}
