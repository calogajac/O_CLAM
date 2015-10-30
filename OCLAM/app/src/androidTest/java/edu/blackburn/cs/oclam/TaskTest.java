package edu.blackburn.cs.oclam;

import junit.framework.TestCase;

/**
 * Created by caleb.long on 10/29/2015.
 */
public class TaskTest extends TestCase {

    public void testGetName() throws Exception {
        Task test = new Task("Greg", "null", "null", false);
        assertEquals("Greg",test.getName());
    }

    public void testSetName() throws Exception {
        Task test = new Task("Greg", "null", "null", false);
        test.setName("Carl");
        assertEquals("Carl", test.getName());
    }

    public void testGetDescription() throws Exception {
        Task test = new Task("Greg", "Buttface", "null", false);
        assertEquals("Buttface", test.getDescription());
    }

    public void testSetDescription() throws Exception {
        Task test = new Task("Greg", "null", "null", false);
        test.setDescription("Rugs");
        assertEquals("Rugs", test.getDescription());
    }

    public void testGetTag() throws Exception {
        Task test = new Task("Greg", "null", "Red", false);
        assertEquals("Red", test.getTag());
    }

    public void testSetTag() throws Exception {
        Task test = new Task("Greg", "null", "null", false);
        test.setTag("Blue");
        assertEquals("Blue", test.getTag());
    }

    public void testGetDone() throws Exception {
        Task test = new Task("Greg", "null", "null", false);
        assertEquals(false, test.getDone());
    }

    public void testSetDone() throws Exception {
        Task test = new Task("Greg", "null", "null", false);
        test.setDone(true);
        assertEquals(true, test.getDone());
    }
}