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

        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT Message, Header FROM naritaaDB.StudentMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("Header"));
            // + "Student"
            //list.add(rs.getString("Message"));
            //add date and header 
        }
        return list;
    }

    //get an aray list of all of the admin messages
    public ArrayList<String> getAdminMessages() throws SQLException {

        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.AdminMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("Message"));

        }
        return list;
    }//end

    public void updateUserDeatails(String name, String surname, String username, String password, String grade, String id) throws SQLException {
        String query = "Update naritaaDB.Studentstbl set Name = " + name + ",Surname = " + surname + ",Username = " + username + ",Password = " + ",Grade =" + grade + "where id = " + id + ";";
        DB.instance.update(query);
    }

    public String getMessge(String value) throws SQLException {
        String output = "";
        String qur = "SELECT * FROM naritaaDB.StudentMessagestbl WHERE Header = '" + value + "';";
        ResultSet rss = DB.instance.query(qur);
        while(rss.next()){
                String title = rss.getString("Header");
                String topic = rss.getString("Topic");
                String subtopic = rss.getString("Subtopic");
                String message = rss.getString("Message");
//skadh
                output += "Title: " + title + "Topic: " + topic + "Subtopic: " + subtopic + "Messages: " + message;
//               if (value.equals(qur)) {
//            String query = "SELECT * FROM naritaaDB.AdminMessagestbl;";
//            ResultSet rs = DB.instance.query(query);
//            while (rs.next()) {
//                String title = rs.getString("Header");
//                String topic = rs.getString("Topic");
//                String subtopic = rs.getString("Subtopic");
//                String message = rs.getString("Message");
//
//                output += "Title: " + title + "Topic: " + topic + "Subtopic: " + subtopic + "Messages: " + message;
//            }
//
//        }
                
            }
        
    
        return output;
    }

//         for (int i = 0; i < messages.length; i++) {
//
//             if(value.equals(messages[i].getHeader())){
//                ArrayList<String> list = new ArrayList<String>();
//                
//                String query = "SELECT * FROM naritaaDB.AdminMessagestbl;";
//                ResultSet rs = DB.instance.query(query);
//                while (rs.next()) {
//                    String title = rs.getString("Header");
//                    String topic = rs.getString("Topic");
//                    String subtopic = rs.getString("Subtopic");
//                    String message = rs.getString("Message");
//                    
//                    output += "Title: " + title + "Topic: " + topic + "Subtopic: " + subtopic + "Messages: " + message;
//                }
//             }
//         }
//         for (int i = 0; i < messages.length; i++) {
//
//             if(value.equals(messages[i])){
//
//                message += messages[i].getHeader();
//                message += messages[i].getTopic();
//                message += messages[i].getSubTopic();
//                message += messages[i].getNote();
//                message += messages[i].getUser();
}//end of class

