package edu.blackburn.cs.oclam;

/**
 *  This Class is the creator of our task objects
 *  Takes the information gotten from the user interface
 *  Created by caleb.long on 10/27/2015.
 */
public class Task {
    String name;
    String description;
    String tag;
    int done;
    final long id;

    /**
     * Constructor for our task objects
     * @param name: the name of the task created
     * @param description: a short description of what the task is
     * @param tag: what category the task falls under
     */
    public Task( String name, String description, String tag){
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.done = 0;//no booleans in sqlite, only integers 0=false 1=true

        //Calls the DB helper to insert this task into the DB
        id = MainActivity.OCLAMDB.insertTask(name, description, tag, 0);
    }

    /**
     * Getter for the name of the task
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name of the task
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the description of the task
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the task
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the tag of the task
     * @return
     */
    public String getTag() {
        return tag;
    }

    /**
     * Setter for the tag of the task
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Getter for the done tag of the task
     * @return
     */
    public int getDone() {
        return done;
    }

    /**
     * Setter for the done tag of the task
     */
    public void setDone() {
        if(done == 0){
            this.done = 1;
        }else{
            this.done = 0;
        }
    }

    /**
     * Getter for the row id of the task
     * @return
     */
    public long getId(){return id;}


    /**
     * Updates the task with the user's new information and updates the DB
     * @param nname: the new name of the task
     * @param ndescription: the new description of the task
     * @param ntag: the new tag of the task
     */
    protected void updateTask(String nname, String ndescription, String ntag){
        //Update the task object
        setName(nname);
        setDescription(ndescription);
        setTag(ntag);

        //Update the task in the database
        MainActivity.OCLAMDB.changeTask(id, nname, ndescription, ntag, done);
    }
}
