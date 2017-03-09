package com.swjtu.deanstar.androidutil.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by yhp5210 on 2017/3/9.
 */

public class SurfaceViewTeplate extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private int x;
    private int y;
    private Path mPath;
    private Paint mPaint;

    public SurfaceViewTeplate(Context context) {
        super(context);
        initView();
    }
    public SurfaceViewTeplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public SurfaceViewTeplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){

        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        this.setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing  = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while(mIsDrawing){
            draw();
            x += 1;
            y = (int)(100*Math.sin(x*2*Math.PI/180) + 400);
            mPath.lineTo(x,y);
        }
    }

    public void draw(){

        try{
            mCanvas = mHolder.lockCanvas();
            //draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);

        }catch (Exception e){

        }finally{
            if(null != mCanvas){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
