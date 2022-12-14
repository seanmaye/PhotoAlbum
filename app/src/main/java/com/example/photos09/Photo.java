package com.example.photos09;

import android.media.Image;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Photo implements Serializable {
    private static final long serialVersionUID = 1L;

    String uri;
    ArrayList<Tag> tags = new ArrayList<Tag>();
    private boolean isChecked =false;
    public Photo(String uri, String tagName, String tagValue) {
        this.uri = uri;
        tags.add(new Tag(tagName, tagValue));
    }

    public Uri getUri() {
        return Uri.parse(uri);
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void addTag(String tagName, String tagValue) throws IOException {
        tags.add(new Tag(tagName, tagValue));
        User.writeApp(Photos.user);
    }

    public void deleteTag(Tag tag) throws IOException {
        tags.remove(tag);
        User.writeApp(Photos.user);
    }
    public boolean isChecked(){
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
