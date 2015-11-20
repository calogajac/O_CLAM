package edu.blackburn.cs.oclam;

import junit.framework.TestCase;

import edu.blackburn.cs.oclam.Database.Task;

/**
 * Created by caleb.long on 10/29/2015.
 */
public class TaskTest extends TestCase {

    public void testGetName() throws Exception {
        Task test = new Task("Greg", "null", "null", 1);
        assertEquals("Greg",test.getName());
    }

    public void testSetName() throws Exception {
        Task test = new Task("Greg", "null", "null", 2);
        test.setName("Carl");
        assertEquals("Carl", test.getName());
    }

    public void testGetDescription() throws Exception {
        Task test = new Task("Greg", "Buttface", "null", 3);
        assertEquals("Buttface", test.getDescription());
    }

    public void testSetDescription() throws Exception {
        Task test = new Task("Greg", "null", "null", 4);
        test.setDescription("Rugs");
        assertEquals("Rugs", test.getDescription());
    }

    public void testGetTag() throws Exception {
        Task test = new Task("Greg", "null", "Red", 5);
        assertEquals("Red", test.getTag());
    }

    public void testSetTag() throws Exception {
        Task test = new Task("Greg", "null", "null", 6);
        test.setTag("Blue");
        assertEquals("Blue", test.getTag());
    }

    public void testGetDone() throws Exception {
        Task test = new Task("Greg", "null", "null", 7);
        assertEquals(0, test.getDone());
    }

    public void testSetDone() throws Exception {
        Task test = new Task("Greg", "null", "null", 8);
        test.setDone();
        assertEquals(1, test.getDone());
    }

    public void testGetID() throws Exception {
        Task test = new Task("Greg", "null", "null", 9);
        assertEquals(9, test.getId());
    }

}