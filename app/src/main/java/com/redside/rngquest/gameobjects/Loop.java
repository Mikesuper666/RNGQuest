package com.redside.rngquest.gameobjects;

import android.os.Handler;

import java.util.Random;


public class Loop {
    public static int FPS = 60;
    private Handler handler;
    private Runnable runnable;
    private final CoreView c;
    private long beginTime;
    private long timeDiff;
    private int sleepTime;
    private int framesSkipped;
    private int framePeriod = (int) (1000 / FPS);
    private int maxFrameSkips = 5;
    public Loop(CoreView coreView){
        sleepTime = 0;
        c = coreView;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                beginTime = System.currentTimeMillis();
                framesSkipped = 0;
                // Tick and render main manager
                c.tick();
                c.render();
                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int) (framePeriod - timeDiff);
                // If falling behind, skip a few frames and tick without rendering
                while (sleepTime < 0 && framesSkipped < maxFrameSkips) {
                    c.tick();
                    sleepTime += framePeriod;
                    framesSkipped++;
                }
                if (framesSkipped > 0){
                    // Display a warning in system out
                    System.out.println("Can't keep up! Skipped " + framesSkipped + " frames");
                }
                // Re-run the runnable
                handler.postDelayed(runnable, sleepTime);
            }
        };
        // Run the thing for the first time
        handler.post(runnable);
    }
}
