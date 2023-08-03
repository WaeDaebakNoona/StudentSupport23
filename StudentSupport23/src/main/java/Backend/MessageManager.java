/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import backend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Narita
 */
public class MessageManager {
    
    private Message[] messages;

    private int messagesSize;
    private DB db;
    
    public void addStudentMessage(Message m) throws ClassNotFoundException, SQLException{
        
        String queryStr = "INSERT INTO naritaaDB.StudentMessagestbl(Topic, Message, Header)Values('" + m.getTopic() + "','" + m.getNote() + "','" + m.getHeader() + "');";
        DB.instance.update(queryStr);
        messages = new Message[messagesSize];
        messagesSize++;
    
    }
    public String displayStudentMessage() throws SQLException, ClassNotFoundException {
        
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
    
    
    public void addAdminMessage(Message m, Student s ) throws ClassNotFoundException, SQLException{
        //adds message from admin into adminmessages table
        //get username: username is their name and surname @reddam.house
        
        String query = "INSERT INTO naritaaDB.AdminMessagestbl(Student, Message)Values('" + s.getUsername()+ "','" + m.getNote() + "');";
        DB.instance.update(query);
        messages = new Message[messagesSize];
        messagesSize++;
    }
    
    public ArrayList<String> getStudentMessages() throws SQLException{
        
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.StudentMessagestbl;";      
        ResultSet rs = DB.instance.query(query);
        while(rs.next()){
            list.add(rs.getString("Message"));
        }
        return list;
    }
}
