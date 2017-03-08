package com.swjtu.deanstar.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.Log;

/**
 * Created by yhp5210 on 2017/3/8.
 */

public class BitmapUtil {

    private static final String TAG = "BitmapUtil";


    /**
     * 圆角图片工具方法
     * @param bitmap 要处理的Bitmap
     * @param rx The x-radius of the oval used to round the corners
     * @param ry The y-radius of the oval used to round the corners
     */
    public static Bitmap roundRectPicture(Bitmap bitmap, int rx,int ry){
        Paint mPaint = new Paint();
        Bitmap out = Bitmap.createBitmap(bitmap.getWidth()
                ,bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        mPaint.setAntiAlias(true);
        Canvas canvas = new Canvas(out);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), rx, ry, mPaint);
            Log.d(TAG, "Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP"
                    + Build.VERSION.SDK_INT);
        } else {
            Log.d(TAG, "Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP"
                    + Build.VERSION.SDK_INT);
        }
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        return out;

    }

    public static Bitmap circleImageView(Bitmap bitmap){
        Paint mPaint = new Paint();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float raduis;
        Bitmap out = Bitmap.createBitmap(width
                ,height,Bitmap.Config.ARGB_8888);
        mPaint.setAntiAlias(true);
        Canvas canvas = new Canvas(out);
        raduis = ((width > height) ? height : width) / 2.0f;
        canvas.drawCircle(width/2.0f,height/2.0f,raduis,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        return out;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps     both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        //首先设置inJustDecodeBounds = true来解码用来检查尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
