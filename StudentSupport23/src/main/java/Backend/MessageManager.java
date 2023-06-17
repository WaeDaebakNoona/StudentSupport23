/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import backend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Narita
 */
public class MessageManager {
    
    private Message[] messages;
    private int messagesSize;
    private DB db;
    
    public void addStudentNote(Message m) throws ClassNotFoundException, SQLException{
        db = new DB();
        String query = "INSERT INTO naritaaDB.StudentMessagestbl(Topic, Message)Values('" + m.getTopic() + "','" + m.getNote() + "');";
        db.update(query);
        messages = new Message[messagesSize];
        messagesSize++;
    
    }
    public String displayStudentMEssage() throws SQLException, ClassNotFoundException {
        db = new DB();
        String query = "SELECT * FROM naritaaDB.StudentMessagestbl;";
        ResultSet rs = db.query(query);
        String output = "";
        while (rs.next()) {
            output += rs.getString("Topic") + ", ";
            output += rs.getString("Message") + "\n";
            output += "\n";
        }
        return output;
    }
    
    
}
