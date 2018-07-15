package ru.dravn.androidlessons.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoMarker implements Target {
    Marker mMarker;
    String mTemp;
    String mName;

    public PicassoMarker(Marker marker, String temp, String name) {
        mMarker = marker;
        mTemp = temp;
        mName = name;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Bitmap tempBitmap = bitmap.copy(bitmap.getConfig(), true);
        Canvas canvas = new Canvas(tempBitmap);

        Paint tempPaint = new Paint();
        tempPaint.setAntiAlias(true);
        tempPaint.setColor(Color.BLACK);
        tempPaint.setTextSize(33.0f);
        tempPaint.setStrokeWidth(2.0f);
        tempPaint.setStyle(Paint.Style.STROKE);

        Paint namePaint = new Paint();
        namePaint.setAntiAlias(true);
        namePaint.setColor(Color.BLACK);
        namePaint.setTextSize(23.0f);
        namePaint.setStyle(Paint.Style.FILL);


        canvas.drawText(mTemp, 10, 30, tempPaint);
        canvas.drawText(mName, 0, 100, namePaint);


        mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(tempBitmap));
        return;
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}