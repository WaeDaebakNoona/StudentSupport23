/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.SQLException;

/**
 *
 * @author Narita
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        StudentManager sm = new StudentManager();
        Student s = new Student("Maa", "Do", "abbott.ans", "12", "1234");
        //it works! create username
        System.out.println(sm.createUsername("Maya","Rivera" ));
        sm.addStudents(s);
    }
    
}
