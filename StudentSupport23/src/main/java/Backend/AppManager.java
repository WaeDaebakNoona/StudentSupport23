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
public class AppManager {
    public static MessageManager messageManager;
    public static StudentManager studentManager;
    
    
    public static void init() throws ClassNotFoundException, SQLException{
        messageManager = new MessageManager();
        studentManager = new StudentManager();
        DB.init();
    }
}
