/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.*;
/**
 *
 * @author memphmane86
 */
public class JDBC {
    // JDBC driver name and database URL
    
    static final String DB_URL = "jdbc:derby://localhost:1527/Employees";
    
    // Database credentials
    static final String USER = "username";
    static final String PASSWORD = "password";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Connection conn = null;
       Statement stmt = null;
       
        try {
            
            // STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            // STEP 3: Open a connection
            System.out.println("Connecting to Database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            
            // STEP 4: Execute a Query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Workers";
            ResultSet rs = stmt.executeQuery(sql);
            
            // STEP 5: Extract data from result set
            while(rs.next()){
                // Retroeve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");
                
                // Display values
                System.out.println("ID: " + id );
                System.out.println(", Age: " + age );
                System.out.println(", First: " + first);
                System.out.println(", Last: " + last );
            
           }
            
            // STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
            
        }catch (Exception e){
            // Handles rrors for Class.forName
            e.printStackTrace();
        
        }finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                stmt.close();
                
            } catch (Exception e) {
                // Nothing we can do
            }
            try {
                if (conn !=null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }// end finally try
        
        }//end try
            System.out.println("Goodbye!");
    }// end main
    
}// end JDBC
