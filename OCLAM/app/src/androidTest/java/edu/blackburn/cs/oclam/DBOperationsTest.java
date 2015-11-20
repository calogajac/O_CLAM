package edu.blackburn.cs.oclam;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.test.IsolatedContext;
import android.test.RenamingDelegatingContext;

import junit.framework.TestCase;

import edu.blackburn.cs.oclam.Database.DBOperations;
import edu.blackburn.cs.oclam.Database.Task;
import edu.blackburn.cs.oclam.UI.MainActivity;

/**
 * Created by caleb.long on 11/12/2015.
 */
public class DBOperationsTest extends AndroidTestCase {

    private DBOperations testDB;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        testDB = new DBOperations(context);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        testDB.close();
        testDB = null;
    }

    public void testInsertTask() throws Exception {
        testDB.open();
        long lid = testDB.insertTask("Fool", "Robot", "ArmchairFace", 0);
        assertTrue(lid != -1);
    }

    public void testChangeTask() throws Exception {
        testDB.open();
        long lid = testDB.insertTask("Name", "Face", "Nose", 0);
        testDB.changeTask(lid, "Baby", "Face", "Nose", 0);
        Cursor testThing = testDB.getTask(lid);
        String name = testThing.getString(testThing.getColumnIndexOrThrow(DBOperations.TASK_NAME));
        String description = testThing.getString(testThing.getColumnIndexOrThrow(DBOperations.TASK_DESCRIPTION));
        String tag = testThing.getString(testThing.getColumnIndexOrThrow(DBOperations.TASK_TAG));
        int id = testThing.getInt(testThing.getColumnIndexOrThrow(DBOperations.TASK_ID));
        int done = testThing.getInt(testThing.getColumnIndexOrThrow(DBOperations.TASK_DONE));
        assertEquals("Baby", name);
        assertEquals("Face", description);
        assertEquals("Nose", tag);
        assertEquals(lid, id);
        assertEquals(0, done);
    }

    public void testDeleteTask() throws Exception {
        testDB.open();
        long lid = testDB.insertTask("Name", "Face", "Nose", 0);
        boolean gone = testDB.deleteTask(lid);
        assertEquals(true, gone);
    }

}