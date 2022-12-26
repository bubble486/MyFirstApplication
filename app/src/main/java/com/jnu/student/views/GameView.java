package com.jnu.student.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public GameView(Context context) {
        super(context);
        initView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread = null;
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        drawThread = new DrawThread();
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        drawThread.stopThread();
    }
    class DrawThread extends Thread{
        private boolean isDrawing = true;

        public void stopThread(){
            isDrawing = false;
            try {
                join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            super.run();
            while (isDrawing){
//                drawing
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.GRAY);
                }catch (Exception e){

                }finally {
                    if(null!=canvas)surfaceHolder.unlockCanvasAndPost(canvas);
                }


                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
