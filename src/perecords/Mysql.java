/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perecords;
import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author BPRAdmin
 */
public class Mysql {
    boolean conStatus;
     Connection con;Statement stmt;ResultSet rs=null;ResultSet result;
      Mysql(String host,String user,String pwd,String db){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://"+host+"/"+db,user,pwd);
        System.out.print("\nConnected MySQL Server\n");
    }catch(Exception ex){
        //ex.printStackTrace();
        JOptionPane.showMessageDialog(null,"Sorry Server Connection is Offline");
       // System.out.print(ex.getMessage()+"\nSorry no MySQL Connection please start MySQL Server\n"+host+"=>"+user+"=>"+db+"=>"+pwd);
        System.exit(0);
    }
    }
    public void executer(String category,String query){
        switch(category){
            case "insert":
            case "update":
            case "delete":
            queryExec(query,category+" Successfull Done");
                break;
            case "select":
                result=getData(query);
                break;
            default:
        }
    }
    public void queryExec(String query,String msg){
    try{
        Statement st=con.createStatement();
        if(st.executeUpdate(query)==1){
             JOptionPane.showMessageDialog(null,msg);
        }else{
         JOptionPane.showMessageDialog(null,"Failed to execute query fst");   
        }
    }catch(Exception ex){
        System.out.println("ERROR: "+ex.getMessage());
        JOptionPane.showMessageDialog(null,"Failed to execute query scnd");
        ex.printStackTrace();
        
    }
}
    public ResultSet getData(String query){
        try{
        stmt=con.createStatement();
        rs=stmt.executeQuery(query);
        }catch(SQLException sqlexc){
            System.out.println(sqlexc.getMessage());
        }
        return rs;
    }
}