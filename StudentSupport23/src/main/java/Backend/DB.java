/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author Cliftonb
 */
public class DB {
    // SINGLETON PATTERN
    //1.   DB.init(); //initialize database
    //2.   DB.instance.query(string);
    //3.   DB.instance.update(string);
    
    //CANT DO THIS (outside this class as constructor is private)
    //4.   DB db = new DB();
    
        public static DB instance;

	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://102.130.115.69:3306/naritaaDB";
        //102.130.115.69 
        //localhost
        //naritaaDB
	private static final String username = "naritaa";
	private static final String password = "Reddam2021";

	private PreparedStatement statement;
	private ResultSet result;
	private Connection connection;

        public static void init() throws ClassNotFoundException, SQLException{
            if(instance == null){
                instance = new DB();
            }
        }
        
        //can only call constructor from within class
	private DB() throws ClassNotFoundException, SQLException {
		//remember to add the mysql-connector-java library to the dependency list
		Class.forName(driver);
		System.out.println("DB: Database driver loaded");
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("DB: Database connection established");
	}
        //update
        //delete
        //insert
	public void update(String update) throws SQLException {
		statement = connection.prepareStatement(update);
		statement.executeUpdate();
		statement.close();
	}
        //select
	public ResultSet query(String stmt) throws SQLException {
		statement = connection.prepareStatement(stmt);
		result = statement.executeQuery();
		return result;
	}

	public static String toString(ResultSet rs) {
		String temp = "";
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int size = meta.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= size; i++) {
					Object value = rs.getObject(i);
					temp += "#" + value;
				}
				temp += "\n";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

}