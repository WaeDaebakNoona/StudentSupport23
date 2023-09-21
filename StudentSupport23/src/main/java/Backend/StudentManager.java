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
    
    private String currentStudent = "";
    
    
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
       
        return list;
    }
    
    //check username and password validity
    //compares username and password in input to database
    public boolean checkValidity(String username, String password) throws SQLException{
        
        Boolean bool = false;
        String query = "SELECT Username, Password FROM naritaaDB.Studentstbl;";
        ResultSet rs = DB.instance.query(query);
        String user = " ";
        String pass = " ";
        while(rs.next()){
            user = rs.getString("Username");
            pass = rs.getString("Password");
            
            if(username.equalsIgnoreCase(user)|| password.equals(pass)){
                bool = true;
                break;
            }
        }
        
        return bool;
    }//end of method
    
    //works in my head, dont know if it actually works
    public void setCurrentStudent(String student){
        
        for (int i = 0; i < students.length; i++) {
            if(students[i].equals(student)){
                currentStudent = students[i].getUsername();
            } 
        }
       
    }
    public String getCurrentStudent(){
        return currentStudent;
    }

    public Student getStudentInfo(String user) throws SQLException{
        String query = "SELECET * FROM naritaaDB.Studentstbl WHERE Username like '" + user+ "';";
        ResultSet rs = DB.instance.query(query);
        while(rs.next()){
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String grade = rs.getString("Grade");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            
            // = new Student(name, surname, grade, username, password);
        }
        return ;
    }
    
}
