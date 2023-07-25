/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import backend.DB;
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
    
    public void addStudents(Student s) throws SQLException, ClassNotFoundException{
        String query = "INSERT INTO `naritaaDB`.`Studentstbl` (`Name`, `Surname`, `Grade`, `Username`, `Password`)"
                + " VALUES (' " + s.getName() + "', '" + s.getSurname() + "', '" + s.getGradeClass()+ "', '" + s.getUsername() + "', '" + s.getPassword() + "');";
        DB.instance.update(query);
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
    
    public ArrayList<String> getStudentsUsernameList() throws SQLException{
        
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM naritaaDB.Studentstbl;";      
        ResultSet rs = DB.instance.query(query);
        while(rs.next()){
            list.add(rs.getString("Username"));
        }
        
        //SELECT AVG(age) FROM Students;
        //rs.next();
        //double avg = rs.getDouble(1);
       
        return list;
    }
    
    
    
}
