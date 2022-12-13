package com.example.photos09;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public static final String storeDir = "."; //in the slides this said "dat" but we want to look in the current directory so use "."
    public static final String storeFile = "users.dat";
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

    // return the actual photo? might have to deal with duplicate photos tho if there are multiple values for same name
    // we would also have to walk it for tag values to display, which might just be more work
    public ArrayList<String> getMatchingTags(String tagName, String tagTest) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Album a : albums) {
            for (Photo p : a.photos) {
                for (Tag t : p.tags) {
                    if (t.name.equalsIgnoreCase(tagName)) {
                        String value = t.value;
                        if (value.length() > tagTest.length() && value.substring(0, tagTest.length()).equals(tagTest)) {
                            if (!ret.contains(tagTest)) {
                                ret.add(value);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }



    public static void writeApp(User user) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(user);
        oos.close();
    }

    public static User readApp() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(storeDir + File.separator + storeFile));
        User user = (User)ois.readObject();
        ois.close();
        return user;
    }
}

