package com.app.vkrsafonenko.tools.FileHandlers;

import com.app.vkrsafonenko.tools.FileHandlers.FileRdrResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileRdr {
    private FileRdrResult result = new FileRdrResult();

    public FileRdr() {

    }

    public FileRdrResult getResult(){
        return result;
    }
    public FileRdr(String file, boolean createIsNotExist) {
        try {
            result.setText(Read(file));
        } catch (FileNotFoundException e) {
            if(createIsNotExist){
                File targetFile = new File(file);
                try {
                    targetFile.createNewFile();
                    result.setFileExist(false);
                    result.setFileIsEmpty(true);
                } catch (IOException ex) {
                    result.setFileExist(false);
                }
            }
        }
    }
    public String Read(String file) throws FileNotFoundException {
        File myObj = new File(file);
        Scanner myReader = new Scanner(myObj);
        String data = "";
        while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        if(data.length()>0){
            result.setFileIsEmpty(false);
        }else{
            result.setFileIsEmpty(true);
        }
        myReader.close();
        return data;
    }

    public String lastLine( File file ) {
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile( file, "r" );
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for(long filePointer = fileLength; filePointer != -1; filePointer--){
                fileHandler.seek( filePointer );
                int readByte = fileHandler.readByte();

                if( readByte == 0xA ) {
                    if( filePointer == fileLength ) {
                        continue;
                    }
                    break;

                } else if( readByte == 0xD ) {
                    if( filePointer == fileLength - 1 ) {
                        continue;
                    }
                    break;
                }

                sb.append( ( char ) readByte );
            }

            String lastLine = sb.reverse().toString();
            return lastLine;
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null )
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    /* ignore */
                }
        }
    }
}

