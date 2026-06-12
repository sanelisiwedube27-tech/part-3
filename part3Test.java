/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package quickchat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class part3Test {

    private StoredMessagesManager manager;
    private ArrayList<Message> testList;
    private String[] flags;

    @BeforeEach
    public void setUp() {
        manager = new StoredMessagesManager(10);
        testList = new ArrayList<>();
        
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        Message m4 = new Message(4, "0838884567", "It is dinner time !");
        m4.setCustomIDAndHash("0838884567");
        
        testList.add(m1);
        testList.add(m2);
        testList.add(m3);
        testList.add(m4);
        
        flags = new String[]{"Sent", "Stored", "Disregard", "Sent"};
        manager.populateFromList(testList, flags);
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        String[] sentArray = manager.getSentMessages();
        // Index 0 and 3 are set to Sent messages
        assertEquals("Did you get the cake?", sentArray[0]);
        assertEquals("It is dinner time !", sentArray[3]);
    }

    @Test
    public void testDisplayLongestMessage() {
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expectedLongest, manager.displayLongestStoredMessage());
    }

    @Test
    public void testSearchForMessageID() {
        String expectedOutput = "\"It is dinner time !\"";
        assertEquals(expectedOutput, manager.searchByMessageID("0838884567"));
    }

    @Test
    public void testSearchAllMessagesRegardingParticularRecipient() {
        String expectedOutput = "\"Where are you? You are late! I have asked you to be on time.\"";
    }

    @Test
    public void testDeleteMessageUsingHash() {
        String matchHash = testList.get(1).getMessageHash(); 
        String expectation = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        assertEquals(expectation, manager.deleteMessageByHash(matchHash));
    }
}