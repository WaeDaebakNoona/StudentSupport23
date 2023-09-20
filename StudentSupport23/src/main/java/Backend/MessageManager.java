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

    private Message[] messages = new Message[200];

    private int messagesSize;
    private DB db;

    public void addStudentMessage(Message m) throws ClassNotFoundException, SQLException {
        messagesSize = 10;
        String queryStr = "INSERT INTO naritaaDB.StudentMessagestbl(Topic, Subtopic, Header, Message)Values('" + m.getTopic() + "','" + m.getSubTopic() + "','" + m.getHeader() + "','" + m.getNote() + "');";
        DB.instance.update(queryStr);
        messages = new Message[messagesSize];
        messagesSize++;

//        String queryStr = "INSERT INTO naritaaDB.StudentMessagestbl(User, Topic, Subtopic, Header, Message)Values('" + m.getUser() + "','" + m.getTopic() + "','" + m.getSubTopic() + "','" + m.getHeader() +  "','" + m.getNote() +"');";
//        DB.instance.update(queryStr);
//        messages = new Message[messagesSize];
//        messagesSize++;
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

    public void addAdminMessage(Message m, Student s) throws ClassNotFoundException, SQLException {
        //adds message from admin into adminmessages table
        //get username: username is their name and surname @reddam.house

        String query = "INSERT INTO naritaaDB.AdminMessagestbl(Student, Message)Values('" + s.getUsername() + "','" + m.getNote() + "');";
        DB.instance.update(query);
        messages = new Message[messagesSize];
        messagesSize++;
    }

    public ArrayList<String> getStudentMessages() throws SQLException {
        // returns an array of all student messages
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT Message, Header FROM naritaaDB.StudentMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("Header"));
            
        }
        return list;
    }

    //get an aray list of all of the admin messages
    public ArrayList<String> getAdminMessages() throws SQLException {

        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.AdminMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("Student"));

        }
        return list;
    }//end

    public void updateUserDeatails(String name, String surname, String username, String password, String grade, String id) throws SQLException {
        String query = "Update naritaaDB.Studentstbl set Name = " + name + ",Surname = " + surname + ",Username = " + username + ",Password = " + ",Grade =" + grade + "where id = " + id + ";";
        DB.instance.update(query);
    }

    public String getStudentMessage(String value) throws SQLException {
        //gets all information of message from student
        
        String output = "";
        String qur = "SELECT * FROM naritaaDB.StudentMessagestbl WHERE Header = '" + value + "';";
        
        ResultSet rss = DB.instance.query(qur);
        while(rss.next()){
                String title = rss.getString("Header");
                String topic = rss.getString("Topic");
                String subtopic = rss.getString("Subtopic");
                String message = rss.getString("Message");
                output += "Title: " + title + "\nTopic: " + topic + "\nSubtopic: " + subtopic + "\nMessages: " + message;
                
            }
        return output;
    }//end of getStudentMessage method

    public String getAdminMessage(String value) throws SQLException{
        String output = "";
        String query = "SELECT * FROM naritaaDB.AdminMessagestbl WHERE Student = '" + value + "';";
        ResultSet rs = DB.instance.query(query);
        while(rs.next()){
                String Student = rs.getString("Student");
                String Message = rs.getString("Message");
                
                output += "Student: " + Student + "\nMessage: " + Message ;
                
            }
        
        return output;
    }//end of getAdminMessage
    
    public String getNumMessages() throws SQLException{
        //get the number of total messages from the studentmessages tbl
        //help how do i do this?
        String output = "";
        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        while(rs.next()){
            String count = "";
        }
        return output;
    }//end of method
    
    
    
}//end of class

