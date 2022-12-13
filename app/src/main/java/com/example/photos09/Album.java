package com.example.photos09;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Album implements Serializable{
    private static final long serialVersionUID = 1L;

    String name;
    ArrayList<Photo> photos = new ArrayList<Photo>();

    public Album(String name) {
        this.name = name;
    }

    public void addPhoto(Photo photo) throws IOException {
        photos.add(photo);
        User.writeApp(Photos.user);
    }

    public void removePhoto(Photo photo) throws IOException {
        photos.remove(photo);
        User.writeApp(Photos.user);
    }

    public void movePhoto(Photo photo, Album album) throws IOException {
        album.addPhoto(photo);
        photos.remove(photo);
        User.writeApp(Photos.user);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public String toString() {
        return name;
    }
}
