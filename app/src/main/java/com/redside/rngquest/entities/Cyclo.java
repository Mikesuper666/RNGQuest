package com.redside.rngquest.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.redside.rngquest.managers.CoreManager;
import com.redside.rngquest.managers.HUDManager;
import com.redside.rngquest.utils.Animator;
import com.redside.rngquest.utils.Assets;

import java.util.ArrayList;

public class Cyclo extends Entity{

    private int tick = 0;
    private Animator animator;

    private ArrayList<Bitmap> idleFrames = new ArrayList<>();
    private ArrayList<Bitmap> attackFrames = new ArrayList<>();
    private ArrayList<Bitmap> damageFrame = new ArrayList<>();

    /**
     *
     * @param hp The starting and max HP of the Blob
     * @param atk The attack value of the Blob
     * @param x The x position of the Blob
     * @param y The y position of the Blob
     * @param startingAlpha The starting opacity of the Blob
     */
    public Cyclo(int hp, int atk, int x, int y, int startingAlpha){

        super("Cyclo", hp, atk, x, y, startingAlpha);

        // Add idle frames
        for (int i = 0; i < 4; i++){
            idleFrames.add(Assets.getBitmapFromMemory("sprites_cyclo_idle_" + i));
        }

        // Add attack frames
        for (int i = 0; i < 10; i++){
            attackFrames.add(Assets.getBitmapFromMemory("sprites_cyclo_attack_" + i));
        }

        // Add extra 10 frames in case of lag
        for (int i = 0; i < 10; i++){
            attackFrames.add(Assets.getBitmapFromMemory("sprites_cyclo_attack_0"));
        }

        // Add damage frame (sadly, it has to be a list because it renders the current animator sprite
        damageFrame.add(Assets.getBitmapFromMemory("sprites_cyclo_damage"));

        animator = new Animator(idleFrames);
        animator.setSpeed(250);
        animator.play();
        animator.update(System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(EAState newState){
        super.state = newState;
        switch(newState){
            case IDLE:
                animator.replace(idleFrames);
                animator.setSpeed(250);
                break;
            case ATTACK:
                animator.replace(attackFrames);
                animator.setSpeed(150);
                break;
            case DAMAGE:
                animator.replace(damageFrame);
                animator.setSpeed(250);
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(){
        super.tick();
        // Hover up and down
        if (tick >= 0 && tick <= 30){
            super.y -= HUDManager.getSpeed(CoreManager.height, 1080);
        }
        else if (tick >= 31 && tick <= 60){
            super.y += HUDManager.getSpeed(CoreManager.height, 1080);
        }
        else if (tick == 61){
            tick = 0;
        }
        tick++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Canvas canvas, Paint paint){

        int oldAlpha = paint.getAlpha();
        paint.setAlpha(super.currAlpha);

        drawCenteredBitmap(animator.sprite, canvas, paint, (int) super.x, (int) super.y);
        animator.update(System.currentTimeMillis());

        paint.setAlpha(oldAlpha);
    }


    /**
     * Draws a bitmap, centered to the position given.
     *
     * @param bitmap The {@link Bitmap} image to be drawn
     * @param canvas The {@link Canvas} object to draw to
     * @param paint The {@link Paint} object to draw with
     * @param x The x position of the bitmap
     * @param y The y position of the bitmap
     */
    public void drawCenteredBitmap(Bitmap bitmap, Canvas canvas, Paint paint, int x, int y){
        x -= (bitmap.getWidth() / 2);
        y -= (bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, x, y, paint);
    }
}
