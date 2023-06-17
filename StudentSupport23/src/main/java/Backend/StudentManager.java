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
public class StudentManager {
    private Student[] students = new Student[100];
    private int studentSize = 0;
    private DB db;
    
    public void addStudents(Student s) throws SQLException, ClassNotFoundException{
        db = new DB();
        String query = "INSERT INTO `naritaaDB`.`Studentstbl` (`Name`, `Surname`, `Grade`, `Username`, `Password`)"
                + " VALUES (' " + s.getName() + "', '" + s.getSurname() + "', '" + s.getGradeClass()+ "', '" + s.getUsername() + "', '" + s.getPassword() + "');";
        db.update(query);
        students[studentSize] = s;
        studentSize++;
        
    }//end of addstudent
    
    //using name and surname of the student to create the reddam email which is their username
    public String createUsername(String name, String surname){
        String output = "";
        String fname = name.toLowerCase();
        String lname = surname.toLowerCase();
        output = fname + "." + lname + "@reddam.house";
        return output;
    }
    
    
}
