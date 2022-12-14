package com.example.photos09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Photo> {
    public ListViewAdapter(@NonNull Context context, ArrayList<Photo> photos) {
        super(context, 0, photos);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.single_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Photo currentItem = getItem(position);

        ImageView picture = listItem.findViewById(R.id.imageView2);
        picture.setImageURI(currentItem.getUri());

        return listItem;
    }
}
