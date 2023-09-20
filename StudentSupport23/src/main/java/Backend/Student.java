/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author Narita
 */
public class Student {
    private String name;
    private String surname;
    private String password;
    private String gradeClass;
    private String username;

    
    public Student(String inN, String inS, String inP, String inG, String inU) {
        this.name = inN;
        this.surname = inS;
        this.password = inP;
        this.gradeClass = inG;
        this.username = inU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(String gradeClass) {
        this.gradeClass = gradeClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFullName(){
        String fullName = getName() + " " + getSurname();
        return fullName;
    }
    //using name and surname of the student to create the reddam email which is their username
//    public String createUsername(String name, String surname){
//        String output = "";
//        String fname = name.toLowerCase();
//        String lname = surname.toLowerCase();
//        output = fname + "." + lname + "@reddam.house";
//        return output;
//    }
    
}
