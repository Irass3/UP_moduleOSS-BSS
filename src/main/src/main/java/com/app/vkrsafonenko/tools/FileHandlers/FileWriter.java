package com.app.vkrsafonenko.tools.FileHandlers;

import com.app.vkrsafonenko.DialogWindow.ShowDialogWindow;

import java.io.*;

public class FileWriter {
    public FileWriter(String file, String data)  {
        try {
            File targetFile = new File(file);
            targetFile.createNewFile();
            targetFile.createNewFile();
            PrintWriter writer = null;
            writer = new PrintWriter(file, "UTF-8");
            writer.println(data);
            writer.close();
        } catch(Exception e) {
            try {
                ShowDialogWindow errorWindow = new ShowDialogWindow("Ошибка", "FileWriter: "+e.getMessage()+" file: "+file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
