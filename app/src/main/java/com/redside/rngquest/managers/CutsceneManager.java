package com.redside.rngquest.managers;

import android.graphics.Canvas;
import android.graphics.Color;

import com.redside.rngquest.R;
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
        String[] introTexts = CoreManager.context.getResources().getStringArray(R.array.cutscene_intro);//get array from xml
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
        ticks++;
        if(numberText < textList.size()){
            if(show) {
                HUDManager.displayFadeMessage(textList.get(numberText).getTextToDisplay(),  //text
                        width / textList.get(numberText).getxPosition(),                //position x
                        height / textList.get(numberText).getyPosition(),               //position y
                        (textList.get(numberText).getDuration() - 60),                      //duration (-60 to compensate for fading)
                        textList.get(numberText).getTextSize(), Color.WHITE);               //color
                show = false;
            }
        }else{
            SEManager.playEffect(SEManager.Effect.FADE_TRANSITION, ScreenState.TITLE);
        }
    }

    public void reder(Canvas canvas){

    }

    private void ClearText(){AnimatedTextManager.clear();}
}
