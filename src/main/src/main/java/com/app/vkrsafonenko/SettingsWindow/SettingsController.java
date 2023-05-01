package com.app.vkrsafonenko.SettingsWindow;

import com.app.vkrsafonenko.tools.FileHandlers.FileRdr;
import com.app.vkrsafonenko.tools.FileHandlers.JsonDeserializator;
import com.app.vkrsafonenko.tools.FileHandlers.WriteJSON;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class SettingsController {

    @FXML
    private TextField dbHost;

    @FXML
    private TextField dbName;

    @FXML
    private PasswordField dbPass;

    @FXML
    private TextField dbPort;

    @FXML
    private TextField dbUser;

    @FXML
    void SetWinSaveSettings(MouseEvent event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dbHost", dbHost.getText());
        params.put("dbName", dbName.getText());
        params.put("dbPass", dbPass.getText());
        params.put("dbPort", dbPort.getText());
        params.put("dbUser", dbUser.getText());
        new WriteJSON("config.json", params);
    }

    @FXML
    void initialize() {
        FileRdr reader = new FileRdr("config.json", true);
        JsonDeserializator deserializator = new JsonDeserializator(reader.getResult().getText());
        Map<String, String> kv = deserializator.getResult();
        for(Map.Entry<String, String> entry : kv.entrySet()) {
            switch (entry.getKey()){
                case "dbHost":
                    dbHost.setText(entry.getValue());
                    break;
                case "dbName":
                    dbName.setText(entry.getValue());
                    break;
                case "dbPass":
                    dbPass.setText(entry.getValue());
                    break;
                case "dbPort":
                    dbPort.setText(entry.getValue());
                    break;
                case "dbUser":
                    dbUser.setText(entry.getValue());
                    break;
            }
        }
    }
}