package com.example.julia.stackoverflowandroid.models;

/**
 * Created by julia on 01/12/16.
 */

public class Item {

    public int count;
    public String name;

    public String getName() { return name;}
    public String toString() {
        return name+"                                            "+count;
    }

}
