package main.java.dam;

//import sql libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;


public final class Database{
    /*
        https://docs.microsoft.com/en-us/sql/connect/jdbc/building-the-connection-url?view=sql-server-ver15
        https://www.codejava.net/java-se/jdbc/connect-to-microsoft-sql-server-via-jdbc
        The general form of the connection URL is
        jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
        private String dbDriver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    */
    
    //attributes (members)
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String connectURL = "jdbc:sqlserver://";
    private static String host;
    private static int port;
    private static String instance = "MSSQLSERVER";
    private static Boolean integrated;
    private static String dbName;
    private static String dbUser;
    private static String dbPassword;

    //constructor
    public static void initDatabase(HashMap<String,String> params) {
        //set the required attributes
        host = params.get("host");
        instance = params.get("instance");
        port = Integer.parseInt(params.get("port"));
        dbName = params.get("dbName");
        integrated = Boolean.parseBoolean(params.get("integrated"));
        dbUser = params.get("dbUser");
        dbPassword = params.get("dbPassword");

        //generate the connection url
        connectURL = generateConnectURL();
    }

    //operations (member functions)
    /**
     * gets connection url to sql server
     * @return String to connect to sql server
     */
    public static String getConnectionURL(){
        return connectURL;
    }

    /**
     * builds connection url to sql server
     * @return String to connect to sql server
     */
    private static String generateConnectURL(){
        //init string builder
        StringBuilder sb = new StringBuilder();
        sb.append(connectURL);

        //append the connection properties
        sb.append(host);

        //if we're not using default instance, append it
        sb.append("\\");
        sb.append(instance);

        //if we're not using default port, append it
        if(port != 1433){
            sb.append(":");
            sb.append(port);
        }

        sb.append(";databaseName=");
        sb.append(dbName);

        sb.append(";integratedSecurity=");
        sb.append(integrated);

        /*
        //if security is not integrated, append user credentials
        if(!this.integrated){
            sb.append(";user=");
            sb.append(this.dbUser);
            sb.append(";password=");
            sb.append(this.dbPassword);
        }
        */

        //return the connection string
        return sb.toString();
    } //end private String generateConnectURL()

    public static Connection connection(){
        try{
            Class.forName(driver);
            return DriverManager.getConnection(connectURL, dbUser, dbPassword);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }    
}