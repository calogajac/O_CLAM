package edu.blackburn.cs.oclam;

import junit.framework.TestCase;

/**
 * Created by caleb.long on 11/12/2015.
 */
public class DBOperationsTest extends TestCase {

    public void testInsertTask() throws Exception {
        Task testThing = new Task("Fool", "Robot", "ArmchairFace");
        assertEquals(-1, testThing.getId());
    }

    public void testChangeTask() throws Exception {
        Task testThing2 = new Task("Fool", "Robot", "ArmchairFace");
        long testThingId = testThing2.getId();
        MainActivity.OCLAMDB.changeTask(testThingId, "Fool", "Robot", "ArmchairFace", 1);
        assertEquals(1, testThing2.getDone());
    }
}