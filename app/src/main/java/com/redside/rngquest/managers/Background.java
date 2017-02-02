package com.redside.rngquest.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.redside.rngquest.utils.Animator;
import com.redside.rngquest.utils.Assets;

import java.util.ArrayList;


public class Background {
    private ArrayList<Bitmap> background;
    private Bitmap clouds, block;
    private Animator animate;
    private int xOff = 0;
    public Background(){
        clouds = Assets.getBitmapFromMemory("background_title_clouds");
        block = Assets.getBitmapFromMemory("sprites_block");
        background = new ArrayList<>();
        background.add(Assets.getBitmapFromMemory("background_title1"));
        background.add(Assets.getBitmapFromMemory("background_title2"));
        animate = new Animator(background);
        animate.setSpeed(450);
        animate.play();
        animate.update(System.currentTimeMillis());
    }
    public void tick(){
        // switch to a on state change later
        switch(CoreManager.state){
            case TITLE:
                for (int i = 1; i < 2; i++){
                    background.set(i, Assets.getBitmapFromMemory("background_title" + i));
                }
                if (xOff < CoreManager.width + clouds.getWidth()){
                    xOff++;
                }else{
                    xOff = 0;
                }
                break;
        }
    }
    public void render(Canvas canvas, Paint paint){
        if (background != null){
            canvas.drawBitmap(animate.sprite, 0, 0, paint);
            animate.update(System.currentTimeMillis());
        }
        switch(CoreManager.state){
            case TITLE:
                canvas.drawBitmap(clouds, CoreManager.width - xOff, CoreManager.height / 10, paint);
                break;

        }
    }
}