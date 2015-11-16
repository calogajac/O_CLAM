package edu.blackburn.cs.oclam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The DB helper class to manage the operations that we will need to run our application
 * Created by amanda.carroll on 11/5/2015.
 */

public class DBOperations {
    //The titles for our rows in our table
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_DESCRIPTION = "task_des";
    public static final String TASK_TAG = "task_tag";
    public static final String TASK_DONE = "task_done";

    //Tag for the log warning
    private static final String TAG = "DBOperations";

    //Names for the DB and the table as well as DB version number
    public static final String DATABASE_NAME = "OCLAM_DB";
    public static final String TABLE_NAME = "Tasks";
    public static final int DATABASE_VERSION = 2;

    //SQL statement for creating the table
    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS Tasks (id integer primary key autoincrement, task_name TEXT, task_des TEXT, task_tag TEXT, task_done INTEGER)";


    //The context of where the DB helper is created
    private final Context context;
    //The DB helper
    private DatabaseHelper DBHelper;
    //The DB
    private SQLiteDatabase db;

    /**
     * The constructor for the DBOperations
     * @param ctx: the context in which the DBOperations is created
     */
    public DBOperations(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /**
     * The Helper class that contains the creation of the DB and the operations we need
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {

        /**
         * Constructor of the Helper that creates or opens the DB
         * @param context: the context that the DB is created
         */
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * Creates the table in the database
         * @param db: our database
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL(TABLE_CREATE);
                Log.d(TAG, "Table Created");
        }

        /**
         * Upgrades the DB to a new version
         * Warns user about the dropping of the table
         * Currently just drops table and then makes a new one(will change later)
         * @param db: the Database
         * @param oldVersion: the old version number
         * @param newVersion: the new version number
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    /**
     * This method opens the database by getting a writable DB
     * @return Returns the writable DB
     * @throws SQLException: catches if the DB cannot be retrieved
     */
    public DBOperations open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    /**
     * This method closes the database so that all info is finished saving
     */
    public void close()
    {
        DBHelper.close();
    }

    /**
     * This Method inserts a task into the database
     * @param name: the name of the task
     * @param description: the description of the task
     * @param tag: the category of the task
     * @param done: the done tag of the task
     * @return returns a long on the status of the insert
     */
    public long insertTask(String name, String description, String tag, int done)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TASK_NAME, name);
        initialValues.put(TASK_DESCRIPTION,description);
        initialValues.put(TASK_TAG, tag);
        initialValues.put(TASK_DONE, done);
        //the insert returns a long that is the id of the row inserted
        return db.insert(TABLE_NAME, null, initialValues);
    }

    /**
     * This method takes in the row id of a task to be deleted from the database and deletes it
     * @param rowId: the primary key for the task
     * @return returns if the deletion was sucessful or not
     */
    public boolean deleteTask(long rowId)
    {
        return db.delete(TABLE_NAME, TASK_ID+ "=" + rowId, null) >0;
    }

    /**
     * This method returns a query of all of the tasks in the database
     * @return returns the results of the query
     */
    public Cursor getAllTasks()
    {
        return db.query(TABLE_NAME, new String[] {TASK_NAME, TASK_DESCRIPTION, TASK_TAG, TASK_DONE},null,null,null,null,null);
    }

    /**
     * This method returns a specific record from the database that matches the given row id
     * @param rowId: the row id to be found
     * @return the matching task to the id given
     * @throws SQLException catches if the query had any issues executing
     */
    public Cursor getRecord(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, TABLE_NAME, new String[] {TASK_NAME, TASK_DESCRIPTION, TASK_TAG, TASK_DONE}, TASK_ID + "="
                        + rowId ,null,null,null,null,null );
        //Moves our query results to the front of the cursor just in case something else is there
        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Updates an existing record in the database with the new information provided
     * @param rowId: the row id of the task to find
     * @param name: the new name of the task
     * @param description: the new description of the task
     * @param tag: the new tag of the task
     * @param done: the new status of if the task is done or not
     * @return returns if the update was successful or not
     */
    public boolean changeTask(long rowId, String name, String description, String tag, int done )
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TASK_NAME, name);
        initialValues.put(TASK_DESCRIPTION,description);
        initialValues.put(TASK_TAG, tag);
        initialValues.put(TASK_DONE, done);
        return db.update(TABLE_NAME, initialValues, TASK_ID + "=" + rowId, null) > 0;
    }
}
