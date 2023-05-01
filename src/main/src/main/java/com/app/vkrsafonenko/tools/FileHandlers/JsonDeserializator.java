package com.app.vkrsafonenko.tools.FileHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDeserializator {
    private Map<String, String> keyVal = new HashMap<>();

    public Map<String, String> getResult(){
        return keyVal;
    }
    public JsonDeserializator(String data)
    {
        data = data.replace("\t", "");
        data = data.replace("\n", "");
        data = removeBrackets(removeBrackets(data, '[', ']'), '{', '}');

        int innerIndexStart=0,innerIndexEnd=data.length();
        readKeysValues(data, "");
    }

    private boolean isSave = false;
    private void readKeysValues(String data, String lastKey) {
        String key = "", val = "";
        List<String> keys = new ArrayList<>();
        char[] arr = data.toCharArray();
        for (int i = 0; i < data.length()-1; i++)
        {
            if(lastKey.length()>0 && lastKey.charAt(lastKey.length()-1)!='\\'){
                lastKey+="\\";
            }
            //чтение ключа
            if (arr[i] == '"' && arr[++i] == ':') {
                int j = i - 2;
                while (arr[j] != '"') {
                    j = j - 1;
                }
                key = "";
                for (int n = j + 1; n <= i - 2; n++) {
                    key += arr[n];
                }
                i+=1;
                keys.add(lastKey+key);
            }
            //
            else if (arr[i] == '[') {
                int lastClosedBracketIndex = searchIndexOfEndsBrackets(arr, i, '[', ']');
                val = data.substring(i+1, lastClosedBracketIndex);
                i = lastClosedBracketIndex;
            }
            //рекурсия чтения вложенности
            else {
                if(arr[i] == '{'){
                    keys.remove(keys.size()-1);
                    int lastClosedBracketIndex = searchIndexOfEndsBrackets(arr, i, '{','}');
                    readKeysValues(data.substring(i+1,lastClosedBracketIndex), lastKey + key);
                    i = lastClosedBracketIndex+1;
                }
            }
        }

        String[] previousKey, currentKey;

        for(int i=0;i<=keys.size()-1;i++){
            previousKey = keys.get(i).split("\\\\");

            String k1 = "\"" + previousKey[previousKey.length-1] + "\":";
            int inx1 = data.indexOf(k1) + k1.length();
            int inx2 = data.length()-1;
            if (i-1 < keys.size() - 1)
            {
                if(i==keys.size()-1){
                    if(keys.size() == 1){
                        for(int j=inx1;j<data.length();j++){
                            if (data.charAt(j) == '"' && data.charAt(++j) == ':') {
                                while (data.charAt(j)!=' '){
                                    j--;
                                }
                                val = data.substring(inx1, j);
                                val = val.trim();
                                break;
                            }
                        }
                    }else{
                        inx2 = data.length();
                    }
                }else{
                    currentKey = keys.get(1+i).split("\\\\");
                    inx2 = data.indexOf("\""+currentKey[currentKey.length-1]+"\":");
                }
            }
            if(val.length()==0){
                val = data.substring(inx1, inx2).trim();
            }
            if(val.charAt(val.length()-1)==','){
                val = val.substring(0, val.length()-1);
            }
            val = removeBrackets(val, '"','"');
            if(val == null){
                val = null;
            }
            keyVal.put(keys.get(i), val);
            val = "";
        }
    }

    private int searchIndexOfEndsBrackets(char[] arr, int startIndex, char brack1, char brack2){
        int cntr = 0, lastClosedBracketIndex = arr.length;
        for(int br = startIndex; br < arr.length; br++){
            if(arr[br] == brack1){
                ++cntr;
            }
            else if(arr[br] == brack2){
                if(cntr==1){
                    lastClosedBracketIndex = br;
                    br = arr.length;
                }else{
                    --cntr;
                }
            }
        }
        return lastClosedBracketIndex;
    }
    private String removeBrackets(String data, char Br1, char Br2)
    {
        String newData = data;
        if (data.charAt(0) == Br1 && data.charAt(data.length() - 1) == Br2)
        {
            newData = data.substring(1, data.length() - 1);
        }
        return newData;
    }
}
