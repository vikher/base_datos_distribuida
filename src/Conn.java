/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

public class Conn {
    public String Gestor;

    public Conn(String Gestor) {
        this.Gestor = Gestor;
    }
    
   
    public static Connection conectar(String Gestor, String Base) throws SQLException{
        
       if(Gestor.equals("SQL")) {
        Connection cn=null;
        try {
            

            /* con ODBC
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           cn = DriverManager.getConnection("jdbc:odbc:miBase","sa","sasa");
           return (cn); */
  
            String userName = "sa";
            String password = "Slimshady2.0";

            String url = "jdbc:sqlserver://localhost:1433;databaseName=TallerPB";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, userName, password);
           
                       /*Con driver*/
           return (cn);
        } catch (Exception e) {
            System.out.print("Error al conectar con la base de dtos:" + e);
            return null;
        }
    }
       else if(Gestor.equals("MySQL")){
            Connection cn=null;
           try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://192.168.43.26:3306/test2", "USERNAME", "PASSWORD");
            return cn;
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return null;
       }
    
    public static JComboBox llenarcombo(JComboBox selector, String query, String gestor, String base) throws SQLException{
        selector.removeAllItems();
        selector.addItem("Selecciona--");
        Connection cn = conectar(gestor, base);
        if (cn != null){
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    selector.addItem(rs.getString(1));
                }
                return selector;
            } catch (Exception e) {
                System.out.println("Error el llenar el mcombo " + e);
               
            }
        }else{
            System.out.println("Error al conectar la base");
        }
        return selector;
        
    }
    
    }


