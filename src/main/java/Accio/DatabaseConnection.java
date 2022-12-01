package Accio;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    static Connection connection = null;  // connection object that will be going to connect

    public static Connection getConnection(){
        if(connection!=null)
        {
            return connection;
        }
        String db = "acciosearch";
        String user = "root";
        String pwd = "2001Saurabh@yadav";
        return getConnection(db,user,pwd);
    }
    private static Connection getConnection(String db,String user,String pwd){
        try{
          Class.forName("com.mysql.cj.jdbc.Driver"); //mysql driver code id downloaded
            // after downloaded to library this forName is used to load that driver into the code
        connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pwd);


        }
        catch(Exception exception){
           exception.printStackTrace();
        }
        return connection;
    }
}
