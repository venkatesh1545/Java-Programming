 package JDBC;
 import java.sql.*;
 
 public class DbConnectionInsertQuery {
     public static void main(String[] args) {
         try {
             String url = "jdbc:mysql://localhost:3306/jdbc_temp";
             String username = "root";
             String password = "12341234";
 
             Connection con = DriverManager.getConnection(url, username, password);
 
             if (con != null) {
                 System.out.println("Successfully connected");
             }
 
             Statement st = con.createStatement();
             st.executeUpdate("insert into student values(514,'venkat','1045997')");
 
             System.out.println();
         } catch (SQLException e) {
             System.out.println(e);
         }
 
     }
 }