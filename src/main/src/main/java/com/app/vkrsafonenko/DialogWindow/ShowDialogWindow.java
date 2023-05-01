package com.app.vkrsafonenko.DialogWindow;

import com.app.vkrsafonenko.HelloApplication;
import com.app.vkrsafonenko.SettingsWindow.ShowSettingsWindow;
import com.app.vkrsafonenko.tools.FileHandlers.FileWriter;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;

public class ShowDialogWindow {

    public ShowDialogWindow(DialogWindowParam param) throws IOException {
        //чтение данных из файла конфигурации окна
        File targetFile = new File("DialogWindowParam.conf");
        targetFile.createNewFile();
        PrintWriter writer = new PrintWriter("DialogWindowParam.conf", "UTF-8");
        writer.println(param.getTitle()+"//");
        writer.println(param.getMessasge());
        writer.close();

        //создание окна
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("errorWindow.fxml"));
            Stage stage = new Stage();
            ModuleLayer.Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(fxmlLoader.load(), 360, 275);
            stage.setTitle(param.getTitle());
            stage.setScene(scene);
            stage.show();

            //Событие закрытия диалогового окна
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    //Открытие последующего окна в зависимости от параметров
                    switch (param.getOpenAfterClosing()){
                        case "Settings":
                            openSettings();
                            break;
                        default:
                            System.out.println("ShowDialogWindow: Неизвестная команда");
                            break;

                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openSettings(){
        ShowSettingsWindow settings = new ShowSettingsWindow();
    }
    public ShowDialogWindow(String title, String message) throws IOException {
        FileWriter writer = new FileWriter("DialogWindowParam.conf", title+"//"+message);

        //создание окна
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("errorWindow.fxml"));
            Stage stage = new Stage();
            ModuleLayer.Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(fxmlLoader.load(), 360, 275);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
