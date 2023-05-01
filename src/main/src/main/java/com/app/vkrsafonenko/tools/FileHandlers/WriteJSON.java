package com.app.vkrsafonenko.tools.FileHandlers;

import com.app.vkrsafonenko.DialogWindow.ShowDialogWindow;

import java.io.IOException;
import java.util.Map;

public class WriteJSON {
    public WriteJSON(String file, Map<String, String> data) {
        String json = "{";
        int i=0;
        for(Map.Entry<String, String> entry : data.entrySet()) {
            json += "\""+entry.getKey()+"\":";
            json += "\""+entry.getValue()+"\"";
            if(i<data.size()-1){
                json+=",";
            }
            ++i;
        }
        json+="}";
        new FileWriter(file, json);
    }
}
