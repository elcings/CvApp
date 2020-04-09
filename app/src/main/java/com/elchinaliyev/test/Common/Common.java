package com.elchinaliyev.test.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;



public class Common {
    public byte[] ImageToByte(ImageView imageView, int newWidth, int newHeight) {
        Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap resize = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        resize.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }
    public Bitmap ConvertByteToBitmap(byte[]image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
