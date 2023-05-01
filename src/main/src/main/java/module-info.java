module com.app.vkrsafonenko {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.app.vkrsafonenko to javafx.fxml;
    exports com.app.vkrsafonenko;
    exports com.app.vkrsafonenko.DialogWindow;
    opens com.app.vkrsafonenko.DialogWindow to javafx.fxml;
    opens com.app.vkrsafonenko.SettingsWindow to javafx.fxml ;
}