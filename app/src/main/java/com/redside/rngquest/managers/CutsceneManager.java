package com.redside.rngquest.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.redside.rngquest.R;
import com.redside.rngquest.models.TextList;
import com.redside.rngquest.utils.Assets;
import com.redside.rngquest.utils.Clock;

import java.util.ArrayList;
import java.util.Objects;

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
    private boolean showText = true;
    private boolean showImage = false;
    private int numberText, ticks;
    private ArrayList<TextList> textList;
    private Background background;
    private Bitmap[] bgs = new Bitmap[6];
    private double[] xOffBG = new double[6];
    private int transition[] = CoreManager.context.getResources().getIntArray(R.array.transitions);//get array from xml

    CutsceneManager(){
        width = HUDManager.width;
        height = HUDManager.height;
        String[] introTexts = CoreManager.context.getResources().getStringArray(R.array.cutscene_intro);//get array from xml
        textList = new ArrayList<>(TextListManager.ManageText(introTexts)); //fills the list with the content kept in xml
        background = new Background();
        //set images to memory
        for(int i=0;i<6;i++){
            bgs[i] = Assets.getBitmapFromMemoryFullscreen("background_intro_"+i);
        }
    }

    public void init(){
        Clock.chronometer();
    }

    /*
     *ticks manipulates the logic of texts and movements displayed on the screen,
     * updated their respective positions and states
     */
    public void ticks() {
        ticks++;
        if (numberText < textList.size()) {
            if (showText) {
                HUDManager.displayFadeMessage(textList.get(numberText).getTextToDisplay(),  //text
                        width / textList.get(numberText).getxPosition(),                //position x
                        height - textList.get(numberText).getyPosition(),               //position y
                        (textList.get(numberText).getDuration() - 60),                      //duration (-60 to compensate for fading)
                        textList.get(numberText).getTextSize(), Color.WHITE);               //color
                showText = false;
            }

            if (ticks == textList.get(numberText).getDuration()) {
                showText = true;
                ticks = 0;
                numberText++;
            }



        }else {
            ClearText();//free memory
            SEManager.playEffect(SEManager.Effect.FADE_TRANSITION, ScreenState.TITLE);//goto title screen
        }
    }

    public void render(Canvas canvas) {
        if (numberText < transition.length) {
            if (transition[numberText] == 1){

                xOffBG[3] = background.offsetScrolling(canvas, bgs[3], xOffBG[3], 1800, true);//stars
                xOffBG[5] = background.offsetScrolling(canvas, bgs[5], xOffBG[5], 2500, false);//moon
                xOffBG[4] = background.offsetScrolling(canvas, bgs[4], xOffBG[4], 3000, false);//castle
                xOffBG[2] = background.offsetScrolling(canvas, bgs[2], xOffBG[2], 800, true);//florest back
                xOffBG[1] = background.offsetScrolling(canvas, bgs[1], xOffBG[1], 500, true);//florest front
            }else{
                canvas.drawBitmap(Objects.requireNonNull(Assets.getBitmapFromMemoryFullscreen("background_black")), 0, 0, null);
            }
        }
    }


    private void ClearText(){AnimatedTextManager.clear();}
}
