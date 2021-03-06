package edu.blackburn.cs.oclam.Database;


import android.database.Cursor;
import java.util.ArrayList;
import edu.blackburn.cs.oclam.UI.MainActivity;

/**
 * Class that creates our list of tasks as well as initializes and opens the Database
 * Is the only class to communicate with the Database
 */
public class TaskList {

    //This variable is our DB helper that will have the methods for using our DB
    //Protected so the project can see it but not the world
    protected static DBOperations OCLAMDB;
    private Cursor ALLTASKS;
    public static ArrayList tasksBag = new ArrayList();
    public static ArrayList<String> taskNames = new ArrayList<String>();

    public TaskList(){
        openDB();
        ALLTASKS = OCLAMDB.getAllTasks();
        collectTasksFromDB();
    }

    /**
     * This method keeps our onCreate method clear of unneeded code
     * Creates the DB helper and opens up our database for the application's use
     * Tested through the DBOperations tests
     */
    private void openDB(){
        OCLAMDB = new DBOperations(MainActivity.oclamContext);
        OCLAMDB.open();
    }

    /**
     * This method keeps our onDestroy from having unneeded code
     * Calls the DB's close method
     * Tested through the DBOperations tests
     */
    private void closeDB(){
        OCLAMDB.close();
    }

    /**
     * Updates the task with the user's new information and updates the DB
     * @param nname: the new name of the task
     * @param ndescription: the new description of the task
     * @param ntag: the new tag of the task
     */
    public void updateTask(int index, String nname, String ndescription, String ntag){
        Task uTask = (Task) tasksBag.get(index);
        uTask.updateTask(nname, ndescription, ntag);
        //Update the task in the database
        OCLAMDB.changeTask(uTask.getId(), nname, ndescription, ntag, 0);
    }

    /**
     * Method that deletes the task sent to it from the program
     * @param rTask: the task that we need to delete
     */
    public void deleteTask(Task rTask){
        //removes the task from the arraylists
        tasksBag.remove(rTask);
        taskNames.remove(rTask.getName());
        //remove the task from the database
        OCLAMDB.deleteTask(rTask.getId());
    }

    /**
     * Basic method to add a task to the array list of tasks
     * @param task: the task to be added
     */
    public void insertTaskToList(Task task){
        //inserts into list
        tasksBag.add(task);
        taskNames.add(task.getName());
    }

    /**
     * Makes a query to the Database to get all tasks
     * Creates task objects of the information
     * Then calls the insert method to insert the task
     */
    public void collectTasksFromDB(){
        //Go through cursor and make tasks then call the method to place them in the list
        while(ALLTASKS.moveToNext()){
            String name = ALLTASKS.getString(ALLTASKS.getColumnIndexOrThrow(DBOperations.TASK_NAME));
            String description = ALLTASKS.getString(ALLTASKS.getColumnIndexOrThrow(DBOperations.TASK_DESCRIPTION));
            String tag = ALLTASKS.getString(ALLTASKS.getColumnIndexOrThrow(DBOperations.TASK_TAG));
            int id = ALLTASKS.getInt(ALLTASKS.getColumnIndexOrThrow(DBOperations.TASK_ID));
            Task ntask = new Task(name, description, tag, id);
            insertTaskToList(ntask);
        }
    }

    /**
     * Adds a new task to the Database and then creates a new task object
     * Then calls the insert method to add it to the task list
     * @param name
     * @param description
     * @param tag
     */
    public void createTaskInDB(String name, String description, String tag){
        //take user input and add it to the database
        long lid = OCLAMDB.insertTask(name, description, tag, 0);//catch the row id
    }

    public String getTaskDescription(long id){
        for (int i = 0; i < tasksBag.size(); i++) {
            Task test = (Task) tasksBag.get(i);
            if(id == test.getId()){
                return test.getDescription();
            }
        }
        String nullString = "Description Not Found";
        return nullString;
    }

    public String getTaskTag(long id){
        for (int i = 0; i < tasksBag.size(); i++) {
            Task test = (Task) tasksBag.get(i);
            if(id == test.getId()){
                return test.getTag();
            }
        }
        String nullString = "Description Not Found";
        return nullString;
    }

    public String getTaskName(long id){
        for (int i = 0; i < tasksBag.size(); i++) {
            Task test = (Task) tasksBag.get(i);
            if(id == test.getId()){
                return test.getName();
            }
        }
        String nullString = "Description Not Found";
        return nullString;
    }
}
