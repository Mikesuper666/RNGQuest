package com.redside.rngquest.managers;

import com.redside.rngquest.models.TextList;

import java.util.ArrayList;

public class TextListManager {
    private static ArrayList<TextList> textLists = new ArrayList<>();
    /*
     *This process is to fill array postion
     */
    public  static ArrayList<TextList>ManageText(String[] processText){

        String[] arrayProcess;
        for(int i = 0; i < processText.length; i++){
            TextList newtextList = new TextList();
            arrayProcess = processText[i].split("#");
            newtextList.setTextToDisplay(arrayProcess[0]);
            newtextList.setxPosition(Integer.parseInt(arrayProcess[1]));
            newtextList.setyPosition(Integer.parseInt(arrayProcess[2]));
            newtextList.setDuration(Integer.parseInt(arrayProcess[3]));
            newtextList.setTextSize(Integer.parseInt(arrayProcess[4]));

            textLists.add(newtextList);
        }
        return textLists;
    }
}
