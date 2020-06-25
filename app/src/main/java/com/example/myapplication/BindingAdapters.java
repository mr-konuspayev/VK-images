package com.example.myapplication;

import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.example.myapplication.pojo.Thumb;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class BindingAdapters {
    @BindingAdapter("android:thumb")
    public static void setThumb(AppCompatImageView imageView, List<Thumb> thumbs) {
        if (thumbs.isEmpty())
            return;
        Thumb thumb = thumbs.size() > 3 ? thumbs.get(3) : thumbs.get(2);
        Picasso.get().load(thumb.getSrc())
                .resize(thumb.getWidth(), thumb.getHeight())
                .centerCrop(Gravity.CENTER)
                .into(imageView);
    }

    @BindingAdapter("android:date")
    public static void setThumb(TextView textView, Long date) {
        textView.setText(new Date(date).toString());
    }
}
