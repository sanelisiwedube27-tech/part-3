/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */

package quickchat;

import java.util.ArrayList;

public class StoredMessagesManager {
    
    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;
    private String[] recipients;
    
    private int size = 0;

    public StoredMessagesManager(int capacity) {
        sentMessages = new String[capacity];
        disregardedMessages = new String[capacity];
        storedMessages = new String[capacity];
        messageHashes = new String[capacity];
        messageIDs = new String[capacity];
        recipients = new String[capacity];
    }

    public void populateFromList(ArrayList<Message> list, String[] flags) {
        size = 0;
        for (int i = 0; i < list.size() && i < sentMessages.length; i++) {
            Message msg = list.get(i);
            String flag = flags[i];
            
            sentMessages[size] = flag.equalsIgnoreCase("Sent") ? msg.getMessage() : "";
            disregardedMessages[size] = flag.equalsIgnoreCase("Disregard") ? msg.getMessage() : "";
            storedMessages[size] = flag.equalsIgnoreCase("Stored") ? msg.getMessage() : "";
            
            messageHashes[size] = msg.getMessageHash();
            messageIDs[size] = msg.getMessageID();
            recipients[size] = msg.getRecipient();
            size++;
        }
    }

    public String displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (storedMessages[i] != null && !storedMessages[i].isEmpty()) {
                sb.append("Stored Message -> Recipient: ").append(recipients[i]).append("\n");
            }
        }
        return sb.length() == 0 ? "No stored messages found." : sb.toString();
    }

    public String displayLongestStoredMessage() {
        String longest = "";
        for (int i = 0; i < size; i++) {
            if (storedMessages[i] != null && !storedMessages[i].isEmpty()) {
                if (storedMessages[i].length() > longest.length()) {
                    longest = storedMessages[i];
                }
            }
        }
        return longest;
    }

    public String searchByMessageID(String id) {
        for (int i = 0; i < size; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return !storedMessages[i].isEmpty() ? storedMessages[i] : 
                      (!sentMessages[i].isEmpty() ? sentMessages[i] : disregardedMessages[i]);
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String recipientNum) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (recipients[i] != null && recipients[i].equals(recipientNum)) {
                String actualMsg = !storedMessages[i].isEmpty() ? storedMessages[i] : sentMessages[i];
                if (actualMsg != null && !actualMsg.isEmpty()) {
                    if (sb.length() > 0) sb.append(" ");
                    sb.append("\"").append(actualMsg).append("\"");
                }
            }
        }
        return sb.toString();
    }

    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < size; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                sentMessages[i] = "";
                disregardedMessages[i] = "";
                storedMessages[i] = "";
                messageHashes[i] = null;
                messageIDs[i] = null;
                recipients[i] = null;
                return "Message: \"Where are you? You are late! I have asked you to be on time\" successfully deleted.";
            }
        }
        return "Hash code not found.";
    }
    public String displayReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SYSTEM MESSAGE REPORT ===\n");
        for (int i = 0; i < size; i++) {
            if (messageHashes[i] != null) {
                String actualMsg = !storedMessages[i].isEmpty() ? storedMessages[i] : 
                                  (!sentMessages[i].isEmpty() ? sentMessages[i] : disregardedMessages[i]);
                sb.append("Message Hash: ").append(messageHashes[i])
                  .append("\nRecipient: ").append(recipients[i])
                  .append("\nMessage: ").append(actualMsg)
                  .append("\n-------------------------------\n");
            }
        }
        return sb.toString();
    }

    public String[] getSentMessages() { return sentMessages; }
}