package dbconnect1;

import java.sql.*;
import javax.swing.*;

public class DBConnect1 {


    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/bootcamp";
        String username="root";
        String password="";
        
        Connection conn=null;
        Statement myquery;
        ResultSet results;
        
        String name;
        double balance;
        
        PreparedStatement insertStmt;
        String insertQuery="insert into customers(CustomerName, Balance) values (?,?)";
        
        try {
            conn=DriverManager.getConnection(url, username, password);
            //insert query
            name=JOptionPane.showInputDialog("Enter the Customer Name");
            balance=Double.parseDouble(JOptionPane.showInputDialog("Enter the customer Balance"));
            insertStmt=conn.prepareStatement(insertQuery);
            insertStmt.setString(1,name);
            insertStmt.setDouble(2,balance);
            insertStmt.execute();
            System.out.println("A new record was added!!");
            //select query
            myquery=conn.createStatement();
            results=myquery.executeQuery("select * from customers;");
            while(results.next()) {
                System.out.print(results.getInt(1)+"\t");
                System.out.print(results.getString("CustomerName")+"\t");
                System.out.println(results.getDouble("Balance"));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            System.out.println("Database error!!");
        }
        
    }
    
}
