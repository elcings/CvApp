package com.elchinaliyev.test.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

import com.itextpdf.awt.geom.misc.RenderingHints;
import com.itextpdf.text.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Common {

    public static Bitmap resize(InputStream is, int newWidth, int newHeight) {
        /*BitmapFactory.Options options=new BitmapFactory.Options();
        Bitmap bm=BitmapFactory.decodeStream(is,null,options);
        int height=bm.getHeight();
        int width=bm.getWidth();
        float scaleWidth=((float)newWidth)/width;
        float scaleHeight=((float)newHeight/height);
        Matrix matrix=new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap resize=Bitmap.createBitmap(bm,0,0,width,height,matrix,false);
       // BitmapDrawable bmd=new BitmapDrawable(resize);*/
        Bitmap bm=BitmapFactory.decodeStream(is);
        int nh = (int) ( bm.getHeight() * (512.0 / bm.getWidth()) );
        Bitmap resize = Bitmap.createScaledBitmap(bm,newWidth,newHeight,true);
        return  resize;

    }




}
