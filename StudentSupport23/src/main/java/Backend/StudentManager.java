/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.DB;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Narita
 */
public class StudentManager {

    private Student[] students = new Student[100];
    private int studentSize = 0;
    private static Student currentStudent;

    public void addStudents(String name, String surname, String grade, String username, String password) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `naritaaDB`.`Studentstbl` (`Name`, `Surname`, `Grade`, `Username`, `Password`)"
                + " VALUES (' " + name + "', '" + surname + "', '" + grade + "', '" + username + "', '" + password + "');";
        DB.instance.update(query);

    }//end of addstudent

    //using name and surname of the student to create the reddam email which is their username
    public String createUsername(String name, String surname) {
        String output = "";
        String fname = name.toLowerCase();
        String lname = surname.toLowerCase();
        output = fname + "." + lname + "@reddam.house";
        return output;
    }

    public ArrayList<String> getStudentsUsernameList() throws SQLException {

        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.Studentstbl;";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {
            list.add(rs.getString("Username"));
        }

        return list;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    //check username and password validity
    //compares username and password in input to database
    public boolean login(String username, String password) throws SQLException {

        boolean valid = false;
        String query = "SELECT * FROM naritaaDB.Studentstbl;";
        ResultSet rs = DB.instance.query(query);
        while (rs.next()) {

            int id = rs.getInt("StudentId");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String user = rs.getString("Username");
            String pass = rs.getString("Password");
            String grade = rs.getString("Grade");

            if (username.equalsIgnoreCase(user) && password.equals(pass)) {
                valid = true;
                currentStudent = new Student(id, name, surname, pass, grade, user);
                break;
            }
        }

        return valid;
    }//end of method

    public String getSpecificStudentInfo(String username) throws SQLException {
        String output = "";
        String query = "SELECT * FROM naritaaDB.Studentstbl WHERE Username = '" + username + "';";
        ResultSet rs = DB.instance.query(query);
        
        int id;
        while (rs.next()) {

             id = rs.getInt("StudentId");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String user = rs.getString("Username");
            String grade = rs.getString("Grade");
            
            String messageQuery = "SELECT count(*) FROM naritaaDB.StudentMessagestbl WHERE StudentID = " + id;
            ResultSet rss = DB.instance.query(messageQuery);
            rss.next();
            
            output = "\tStudent Information:\n" + "\t----------------------------" + "\nName:" + name + "\nSurname: " + surname + "\nUsername: " + user + "\nGrade: " + grade + "\nTotal Messages sent: " + rss.getInt(1);;

        }

        return output;
    }//end of method
}//end of class
