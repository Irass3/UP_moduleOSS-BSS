package com.app.vkrsafonenko.SettingsWindow;

import com.app.vkrsafonenko.DialogWindow.ShowDialogWindow;
import com.app.vkrsafonenko.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowSettingsWindow {
    public ShowSettingsWindow(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settingsWindow.fxml"));
            Stage stage = new Stage();
            ModuleLayer.Controller controller = fxmlLoader.getController();
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 600, 370);
            stage.setTitle("Настройки");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            try {
                ShowDialogWindow errorWindow = new ShowDialogWindow("Ошибка", "ShowSettingsWindow: "+e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
