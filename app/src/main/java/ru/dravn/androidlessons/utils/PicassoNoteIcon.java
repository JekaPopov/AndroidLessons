package ru.dravn.androidlessons.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoNoteIcon implements Target {
    NotificationCompat.Builder mBuilder;

    public PicassoNoteIcon(NotificationCompat.Builder builder) {
        mBuilder = builder;
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mBuilder.setLargeIcon(bitmap);
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}