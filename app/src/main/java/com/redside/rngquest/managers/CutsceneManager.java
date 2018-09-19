package com.redside.rngquest.managers;

import android.graphics.Canvas;

import com.redside.rngquest.models.TextList;
import com.redside.rngquest.utils.Clock;

import java.util.ArrayList;

/**
 * Non-interactive event handler
 * This process occurs automatically and can be disabled by touching the screen (not yet implanted)
 * The class is generic and must control elements of the game like: Text, images, etc.
 * @author Maico Ribeiro (mikersuper666)
 * @since September 19, 2018
 */
public class CutsceneManager {
    private static int width;
    private static int height;
    private boolean show = true;
    private int numberText, ticks;
    private ArrayList<TextList> textList;

    CutsceneManager(){
        width = HUDManager.width;
        height = HUDManager.height;
        textList = new ArrayList<>(); //fills the list with the content kept in xml
    }

    public void init(){
        Clock.chronometer();
    }

    /*
     *ticks manipulates the logic of texts and movements displayed on the screen,
     * updated their respective positions and states
     */
    public void ticks(){

    }

    public void reder(Canvas canvas){

    }

    private void ClearText(){AnimatedTextManager.clear();}
}
