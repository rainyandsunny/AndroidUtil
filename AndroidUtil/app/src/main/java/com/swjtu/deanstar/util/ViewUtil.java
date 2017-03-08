package com.swjtu.deanstar.util;

import android.view.View.MeasureSpec;


/**
 * 自定义view用到的工具类
 * Created by yhp5210 on 2017/3/8.
 */

public class ViewUtil {


    /**
     * 得到实际的尺寸
     * @param SizeInfoMeasureSpec 宽还是高()
     * @param limitSize 要求的尺寸
     * @return
     */
    public static int getSize(int SizeInfoMeasureSpec,int limitSize){

        int mode = MeasureSpec.getMode(SizeInfoMeasureSpec);
        int size = limitSize;
        switch(mode){
            case MeasureSpec.EXACTLY:{
                size = MeasureSpec.getSize(SizeInfoMeasureSpec);
            }break;
            case MeasureSpec.AT_MOST:{
                size = Math.min(size,MeasureSpec.getSize(SizeInfoMeasureSpec));
            }break;
            default:{
                size = limitSize;
            }break;
        }
        return size;
    }
}
