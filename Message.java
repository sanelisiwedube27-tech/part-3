/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
package quickchat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;      
import java.util.Random;
import java.util.Scanner;


public class Message {
 
    private String messageID;
    private int numMessages;
    private String recipient;
    private String message;
    private String messageHash;
    
    private static final List<String> messageHistory = new ArrayList<>();

    public Message(int numMessages, String recipient, String message) {
        this.numMessages = numMessages;
        this.recipient = recipient;
        
        this.message = (message == null) ? "" : message; 
        generateMessageID();
        createMessageHash();
        
        messageHistory.add(printMessages());
    }

    public void setCustomIDAndHash(String customID) {
        this.messageID = customID;
        createMessageHash();
    }

    public void generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        messageID = String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    public String checkRecipientCell() {
        if(recipient != null && recipient.startsWith("+") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

    public String checkMessageLength() {
        if(message.length() <= 250) {
            return "Message ready to send.";
        }
        int excess = message.length() - 250;
        return "Message exceeds 250 characters by " + excess;
    }

    public String createMessageHash() {
        String trimmed = message.trim();
        if (trimmed.isEmpty()) {
            messageHash = messageID.substring(0, 2) + ":" + numMessages + ":EMPTY";
            return messageHash;
        }

        String[] words = trimmed.split("\\s+"); 
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        messageHash = messageID.substring(0, 2) + ":" + numMessages + ":" + firstWord + lastWord;
        return messageHash;
    }
    
    public String sentMessage(int option) {
        switch(option) {
            case 1: return "Message successfully sent.";
            case 2: return "Press 0 to delete message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid option.";
        }
    }
    
    public String printMessages() {
        return """
                Message ID: %s
                Message Hash: %s
                Recipient: %s
                Message: %s
                """.formatted(messageID, messageHash, recipient, message);
    }
    
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    
    public static String getMessages() {
        if (messageHistory.isEmpty()) {
            return "No messages have been sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : messageHistory) {
            sb.append(msg).append("\n-------------------------\n");
        }
        return sb.toString();
    }
      private static void storeMessageToTextFile(String id, String recipient, String message, String hash) {
        try (FileWriter file = new FileWriter("stored_message.txt", true)) {
            file.write("Message ID: " + id + "\n");
            file.write("Recipient: " + recipient + "\n");
            file.write("Message: " + message + "\n");
            file.write("Hash: " + hash + "\n");
            file.write("-----\n");
            System.out.println("Message stored successfully in text file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 