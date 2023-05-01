package com.app.vkrsafonenko;

import com.app.vkrsafonenko.SettingsWindow.ShowSettingsWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class HelloController {

    @FXML
    private Button AuthWndwEntireBtn;

    @FXML
    private ImageView AuthWndwImageView;

    @FXML
    private TextField AuthWndwLoginTextField;

    @FXML
    private PasswordField AuthWndwPasswrdTextField;

    @FXML
    void initialize() {
        //установка логотипа для страницы авторизации
        File file = new File("src/assets/previewLogo.png");
        Image image = new Image(file.toURI().toString());
        AuthWndwImageView.setImage(image);
    }
    @FXML
    void AuthWinOpenSettings(MouseEvent event) {
        ShowSettingsWindow settings = new ShowSettingsWindow();
    }
}