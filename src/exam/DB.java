/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB {
     public static Connection DBConnection() {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //cmd :SQLPLUS / AS SYSDBA
            //     ALTER USER HR ACCOUNT UNLOCK;
            //      ALTER USER HR IDENTIFIED BY newpassword;
            String url = "jdbc:oracle:thin:PRODUCT/207@localhost:1521/XE";
           
            conn = DriverManager.getConnection(url);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
