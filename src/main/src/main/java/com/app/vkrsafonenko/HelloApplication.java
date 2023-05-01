package com.app.vkrsafonenko;

import com.app.vkrsafonenko.DialogWindow.DialogWindowController;
import com.app.vkrsafonenko.DialogWindow.DialogWindowParam;
import com.app.vkrsafonenko.tools.FileHandlers.FileRdr;
import com.app.vkrsafonenko.tools.FileHandlers.JsonDeserializator;
import com.app.vkrsafonenko.DialogWindow.ShowDialogWindow;
import com.app.vkrsafonenko.tools.RSLogger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {
    private RSLogger logger = new RSLogger();
    @Override
    public void start(Stage stage) throws Exception {
        //Создание окна авторизации
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 841, 576);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    logger.EndOfLine();
                } catch (IOException e) {
                    try {
                        ShowDialogWindow errorWindow = new ShowDialogWindow("Ошибка", "HelloApplication.start: "+e.getMessage());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        //Чтение конфигурации
        FileRdr reader = new FileRdr("config.json", true);

        //Проверка файла
        if(reader.getResult().isFileIsEmpty()){
            logger.addLog("HelloApplication.start: Файл конфигурации пуст");
            DialogWindowParam param = new DialogWindowParam("Предупреждение", "Отсутствует конфигурация приложения", "Settings");
            ShowDialogWindow errorWindow = new ShowDialogWindow(param);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}