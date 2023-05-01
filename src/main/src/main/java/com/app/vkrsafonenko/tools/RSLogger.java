package com.app.vkrsafonenko.tools;

import com.app.vkrsafonenko.tools.FileHandlers.FileRdr;

import java.io.*;

public class RSLogger {
    private String sessionSeparator = "\n------------------------------------------\n";
    private String logFile = "app.log";
    public RSLogger(){
        try {
            FileRdr reader = new FileRdr();
            File file = new File(logFile);
            if (file.createNewFile()){
                initSession();
            }
            else{
                String lastLine = reader.lastLine(file);
                if(lastLine.length()>0){
                    if(lastLine.equals("eol")){
                        initSession();
                    }
                }else{
                    initSession();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initSession() {
        try(FileWriter writer = new FileWriter(logFile, true))
        {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            String text = "Начало сессии: "+java.time.LocalDateTime.now();
            bufferWriter.write(sessionSeparator);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void EndOfLine() throws IOException {
        addLog("eol");
    }

    public void addLog(String s) throws IOException{
        FileWriter writer = new FileWriter(logFile, true);
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        bufferWriter.write("\n"+s);
        bufferWriter.close();
    }
}
