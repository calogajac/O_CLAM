package edu.blackburn.cs.oclam.Database;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by amanda.carroll on 11/22/2015.
 */
public class TaskListTest extends AndroidTestCase {

    private TaskList testList;
    private DBOperations testDB;
    private Cursor allTasks;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        testDB = new DBOperations(context);
        testDB.open();
        allTasks = testDB.getAllTasks();
    }

    public void testUpdateTask() throws Exception {
        //not implemented yet
    }

    public void testInsertTaskToList() throws Exception {
        Task iTask = new Task("Dog", "Cat", "Bear", 1);
        boolean added1 = testList.tasksBag.add(iTask);
        assertEquals(true, added1);
        boolean added2 = testList.taskNames.add(iTask.getName());
        assertEquals(true, added2);
    }

    public void testCollectTasksFromDB() throws Exception {
        //Every element in this method is tested elsewhere
    }

    public void testCreateTaskInDB() throws Exception {
        long lid = testDB.insertTask("Fool", "Robot", "ArmchairFace", 0);
        assertTrue(lid != -1);
    }
}