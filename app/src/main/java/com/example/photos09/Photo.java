package com.example.photos09;

import android.media.Image;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Photo implements Serializable {
    private static final long serialVersionUID = 1L;

    File file;
    ArrayList<Tag> tags = new ArrayList<Tag>();

    public Photo(File file, String caption, String tagName, String tagValue) {
        this.file = file;
        tags.add(new Tag(tagName, tagValue));
    }

    // what data type?
    public Image getImage() {
        return new Image(file.toURI().toString());
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

}
