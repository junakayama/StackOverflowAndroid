package com.example.julia.stackoverflowandroid.models;

import java.util.List;

/**
 * Created by julia on 03/12/16.
 */

public class Question {
    public String title;
    public Owner owner;
    public int score;
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
