package com.example.photos09;

import java.io.Serializable;
import java.util.ArrayList;

public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String value;

    // before passing to constructor, check if tag name is "location" or "person" ONLY
    public Tag(String name, String value) {
        this.name = name;
        this.value=value;
    }

    public String getTagName() {
        return name;
    }

    public String getTagValue() {
        return value;
    }

    public String toString() {
        return name + " : " + value;
    }
}
