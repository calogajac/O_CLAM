package edu.blackburn.cs.oclam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amanda.carroll on 11/5/2015.
 */

public class DBOperations {
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_DESCRIPTION = "task_des";
    public static final String TASK_TAG = "task_tag";
    public static final String TASK_DONE = "task_done";
    private static final String TAG = "DBAdapter";

    public static final String DATABASE_NAME = "O_CLAM_db";
    public static final String TABLE_NAME = "tasks";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE_TABLE"+ TABLE_NAME + "(" + TASK_NAME
            + " TEXT," + TASK_DESCRIPTION+ "TEXT," + TASK_TAG + "TEXT," + TASK_DONE + "INTEGER );";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBOperations(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try{
                db.execSQL(DATABASE_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    // Opens the Database
    public DBOperations open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Closes the Database
    public void close()
    {
        DBHelper.close();
    }

    // Insert a Task into the Database
    public long insertTask(String name, String description, String tag, int done)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TASK_NAME, name);
        initialValues.put(TASK_DESCRIPTION,description);
        initialValues.put(TASK_TAG, tag);
        initialValues.put(TASK_DONE, done);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    // Deletes a Task from the Database
    public boolean deleteTask(long rowId)
    {
        return db.delete(TABLE_NAME, TASK_ID+ "=" + rowId, null) >0;
    }

    // Gets all Tasks from the Database
    public Cursor getAllTasks()
    {
        return db.query(TABLE_NAME, new String[] {TASK_NAME, TASK_DESCRIPTION, TASK_TAG, TASK_DONE},null,null,null,null,null);
    }

    // Gets a specific Task from the Database
    public Cursor getRecord(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, TABLE_NAME, new String[] {TASK_NAME, TASK_DESCRIPTION, TASK_TAG, TASK_DONE}, TASK_ID + "="
                        + rowId ,null,null,null,null,null );
        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // Makes changes to a specific Task in the Database
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
