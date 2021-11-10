/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runningevents;

import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.util.Properties;

/**
 *
 * @author ricardo
 */

public class Server {
    
    private static String host;
    private static String name;
    private static String username;
    private static String password;
    private static int port;
            
    public static void main(String[] args) throws Exception {
        if(args.length == 1)
            port = Integer.parseInt(args[0]);
        else {
            System.err.println("Error: No port specified.");
            System.exit(1);
        }
        
        
        try { // Try to open the file with properties and read each parameter
            InputStream cfgFile = new FileInputStream("/resources/config.properties");
            Properties config = new Properties();
            config.load(cfgFile);
            host = config.getProperty("db.host");
            name = config.getProperty("db.name");
            username = config.getProperty("db.username");
            password = config.getProperty("db.password");
            System.out.println(host + "" + name + "" + "" + username + "" + password);
        } catch(IOException e) {
            System.err.println("Error: Could not read '/resources/config.properties'.");
            System.exit(1);
        }
        
        dbConnector dbC = new dbConnector(host, name, username, password);
        
        try { // Try to connect to the db specified at 'config.properties'
            dbC.connect();
            Statement stmt = dbC.getStatement();
            
            RunningEvents event = new RunningEvents(stmt);
            
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(port);
            registry.rebind("runningevents", event);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: Could not establish connection to the database.");
        }

        dbC.disconnect();
    }
}
