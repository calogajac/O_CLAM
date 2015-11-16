package edu.blackburn.cs.oclam;

import junit.framework.TestCase;

/**
 * Created by caleb.long on 10/29/2015.
 */
public class TaskTest extends TestCase {

    public void testGetName() throws Exception {
        Task test = new Task("Greg", "null", "null");
        assertEquals("Greg",test.getName());
    }

    public void testSetName() throws Exception {
        Task test = new Task("Greg", "null", "null");
        test.setName("Carl");
        assertEquals("Carl", test.getName());
    }

    public void testGetDescription() throws Exception {
        Task test = new Task("Greg", "Buttface", "null");
        assertEquals("Buttface", test.getDescription());
    }

    public void testSetDescription() throws Exception {
        Task test = new Task("Greg", "null", "null");
        test.setDescription("Rugs");
        assertEquals("Rugs", test.getDescription());
    }

    public void testGetTag() throws Exception {
        Task test = new Task("Greg", "null", "Red");
        assertEquals("Red", test.getTag());
    }

    public void testSetTag() throws Exception {
        Task test = new Task("Greg", "null", "null");
        test.setTag("Blue");
        assertEquals("Blue", test.getTag());
    }

    public void testGetDone() throws Exception {
        Task test = new Task("Greg", "null", "null");
        assertEquals(0, test.getDone());
    }

    public void testSetDone() throws Exception {
        Task test = new Task("Greg", "null", "null");
        test.setDone();
        assertEquals(1, test.getDone());
    }
}