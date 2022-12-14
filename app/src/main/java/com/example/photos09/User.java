package com.example.photos09;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    static File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
    static File store = new File(path, "." + "users.dat");
    private static final long serialVersionUID = 1L;

    ArrayList<Album> albums;

    public User() {
        albums = new ArrayList<Album>();
    }

    public void addAlbum(Album album) throws IOException {
        albums.add(album);
        writeApp(Photos.user);
    }

    public void removeAlbum(Album album) throws IOException {
        albums.remove(album);
        writeApp(Photos.user);
    }

    public void renameAlbum(Album album, String name) throws IOException {
        album.name = name;
        writeApp(Photos.user);
    }

    public ArrayList<Album> getAlbumList() {
        return albums;
    }

    public ArrayList<String> getMatchingTags(boolean person, boolean location, String tagTestValue) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Album a : albums) {
            for (Photo p : a.photos) {
                for (Tag t : p.tags) {
                    if (((person && t.name.equalsIgnoreCase("person")))
                            || location && t.name.equalsIgnoreCase("location")) {
                        String value = t.value;
                        if (value.startsWith(tagTestValue) && !ret.contains(tagTestValue)) {
                            ret.add(value);
                        }
                    }
                }
            }
        }
        return ret;
    }

    public ArrayList<Photo> getSearchResults(boolean person, boolean location, String tagTestValue){
        ArrayList<Photo> ret = new ArrayList<Photo>();
        for (Album a : albums) {
            for (Photo p : a.photos) {
                for (Tag t : p.tags) {
                    if (((person && t.name.equalsIgnoreCase("person"))
                            || (location && t.name.equalsIgnoreCase("location")))
                            && (t.value.equalsIgnoreCase(tagTestValue))
                            && !ret.contains(p)){
                        ret.add(p);
                    }
                }
            }
        }
        return ret;
    }



    public static void writeApp(User user) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(store));
        oos.writeObject(user);
        oos.close();
    }

    public static User readApp() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(store));
        User user = (User)ois.readObject();
        ois.close();
        return user;
    }
}

