/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.DB;
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

    public void addStudentMessage(Message m, int studentId) throws ClassNotFoundException, SQLException {
        messagesSize = 10;
        String queryStr = "INSERT INTO naritaaDB.StudentMessagestbl(StudentID, Topic, Subtopic, Header, Message)Values(" + studentId + ",'" + m.getTopic() + "','" + m.getSubTopic() + "','" + m.getHeader() + "','" + m.getNote() + "');";
        DB.instance.update(queryStr);
        messages = new Message[messagesSize];
        messagesSize++;
    }

    public void addAdminMessage(String username, String message) throws ClassNotFoundException, SQLException {
        //adds message from admin into adminmessages table
        //get username: username is their name and surname @reddam.house
        String getStudentIDquery = "SELECT StudentId FROM naritaaDB.Studentstbl where Username like '" + username + "';";
        ResultSet rs = DB.instance.query(getStudentIDquery);
        rs.next();
        String query = "INSERT INTO naritaaDB.AdminMessagestbl(StudentID, Message)Values('" + rs.getInt(1) + "','" + message + "');";
        DB.instance.update(query);
    }

    public ArrayList<String> getStudentMessages() throws SQLException {
        // returns an array of all student messages ///////
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.StudentMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        int studentID = 0;
        while (rs.next()) {
            list.add(rs.getString("Header"));
            studentID = rs.getInt("StudentID");
            String studentUsernamequery = "SELECT Username from naritaaDB.Studentstbl WHERE StudentId =" + studentID + ";";
            ResultSet rss = DB.instance.query(studentUsernamequery);
            rss.next();
            String username = rss.getString("Username");
            //list.add(rs.getString("Header")+ "|" + username);
        }

        return list;
    }

    public ArrayList<String> getAdminMessages() throws SQLException {
        //get an aray list of all of the admin messages
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.AdminMessagestbl;";
        ResultSet rs = DB.instance.query(query);

        while (rs.next()) {
            list.add(rs.getString("AdminMessagesID"));
            
        }

        return list;
    }//end

    public ArrayList<String> getSpecificStudentMessage(int id) throws SQLException {
        //gets the messages sent from that specific student
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.StudentMessagestbl WHERE StudentID = " + id + ";";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("Header"));
        }
        return list;
    }//end of method

    public ArrayList<String> getSpecificAdminMessage(int studentID) throws SQLException {
        //gets the messages sent from that specific student
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.AdminMessagestbl WHERE StudentID = " + studentID + ";";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("AdminMessagesID"));
        }
        return list;
    }//end of method

    public String getStudentMessage(String value) throws SQLException {
        //gets all information of a message from student
        //this is only for admin

        String output = "";
        String qur = "SELECT * FROM naritaaDB.StudentMessagestbl WHERE Header ='" + value + "';";

        ResultSet rss = DB.instance.query(qur);
        while (rss.next()) {
            String id = rss.getString("StudentId");
            String title = rss.getString("Header");
            String topic = rss.getString("Topic");
            String subtopic = rss.getString("Subtopic");
            String message = rss.getString("Message");

            String getStudentUsername = "SELECT username from naritaaDB.Studentstbl where StudentId = '" + id + "'";
            ResultSet rs = DB.instance.query(getStudentUsername);
            rs.next();

            output += "Student: " + rs.getString("Username") + "\nTitle: " + title + "\nTopic: " + topic + "\nSubtopic: " + subtopic + "\nMessages: " + message;

        }
        return output;
    }//end of getStudentMessage method

    public String getAdminMessage(String value) throws SQLException {
        //gets information from a message
        //value is admin message id
        String output = "";
        String query = "SELECT * FROM naritaaDB.AdminMessagestbl WHERE AdminMessagesID = '" + value + "';";
        ResultSet rs = DB.instance.query(query);
        String get = "";
        while (rs.next()) {
            String messageID = rs.getString("AdminMessagesID");
            String message = rs.getString("Message");
            
            int studentID = rs.getInt("StudentID");
            String usernameQuery = "SELECT Username from naritaaDB.Studentstbl WHERE StudentId =" + studentID + ";";
            ResultSet rss = DB.instance.query(usernameQuery);
            rss.next();
            String username = rss.getString("Username");
            
            output += "MessageID: " + messageID + "\nStudent: " + username+ "\nMessage: " + message ;
            
            
        }

        return output;
    }//end of getAdminMessage

    public String getTotalNumMessages() throws SQLException {
        //get the number of total messages from the studentmessages tbl

        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl;";
        ResultSet rs = DB.instance.query(query);
        rs.next();
        return rs.getString(1);
    }//end of method

    public ArrayList<String> getMessageFromTopic(String value) throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.StudentMessagestbl WHERE Topic like '" + value + "';";
        ResultSet rs = DB.instance.query(query);

        while (rs.next()) {
            list.add(rs.getString("Header"));
        }
        return list;
    }

    // returns total number of messages per topic
    public String getNumAcademic() throws SQLException {
        String output = "";
        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl WHERE Topic like 'Academic';";
        ResultSet rs = DB.instance.query(query);
        rs.next();
        return rs.getString(1);

    }

    public String getNumFacilities() throws SQLException {
        String output = "";
        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl WHERE Topic like 'Facilities';";
        ResultSet rs = DB.instance.query(query);
        rs.next();
        return rs.getString(1);
    }

    public String getNumTeachers() throws SQLException {
        String output = "";
        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl WHERE Topic like 'Teachers';";
        ResultSet rs = DB.instance.query(query);
        rs.next();
        return rs.getString(1);
    }

    public String getNumReddeli() throws SQLException {
        String output = "";
        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl WHERE Topic like 'Red deli';";
        ResultSet rs = DB.instance.query(query);
        rs.next();
        return rs.getString(1);
    }

    public String getNumStudents() throws SQLException {
        String output = "";
        String query = "SELECT count(*) FROM naritaaDB.StudentMessagestbl WHERE Topic like 'Students';";
        ResultSet rs = DB.instance.query(query);
        rs.next();
        return rs.getString(1);
    }

}//end of class

