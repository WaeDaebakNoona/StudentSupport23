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
    private int id;
    private String name;
    private String surname;
    private String password;
    private String gradeClass;
    private String username;

    
    public Student(int inId,String inN, String inS, String inP, String inG, String inU) {
        id = inId;
        this.name = inN;
        this.surname = inS;
        this.password = inP;
        this.gradeClass = inG;
        this.username = inU;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    
}
