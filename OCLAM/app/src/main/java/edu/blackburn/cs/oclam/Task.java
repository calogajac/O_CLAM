package edu.blackburn.cs.oclam;

/**
 * Created by caleb.long on 10/27/2015.
 */
public class Task {
    String name;
    String description;
    String tag;
    int done;

    public Task(String name, String description, String tag){
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.done = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getDone() {
        return done;
    }

    public void setDone() {this.done = 1;}
}
