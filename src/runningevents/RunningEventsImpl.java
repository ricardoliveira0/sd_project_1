/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runningevents;

import java.lang.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 *
 * @author ricardo
 */

public class RunningEventsImpl extends UnicastRemoteObject implements RunningEvents, java.io.Serializable {
    
    static Statement stmt;
    
    public RunningEventsImpl(Statement statement) throws java.rmi.RemoteException {
        this.stmt = statement;
    }
    
    private String echelonInterpreter(int echelon) {
        String fullEchelon = null;
        switch(echelon) {
            case 1:
                fullEchelon = "Juniors";
                break;
            case 2:
                fullEchelon = "Seniors";
                break;
            case 3:
                fullEchelon = "Veterans 35";
                break;
            case 4:
                fullEchelon = "Veterans 40";
                break;
            case 5:
                fullEchelon = "Veterans 45";
                break;
            case 6:
                fullEchelon = "Veterans 50";
                break;
            case 7:
                fullEchelon = "Veterans 55";
                break;
            case 8:
                fullEchelon = "Veterans 60";
                break;
            case 9:
                fullEchelon = "Veterans 65+";
                break;  
        }
        return fullEchelon;
    }

    public void registerEvent(String name, String date, int type) throws RemoteException {
        String fullType = null;
        
        try {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(name) FROM event_list WHERE name = '" + name + "'");
            rs.next();
            if(rs.getInt(1) == 0) {
                switch(type) {
                    case 1:
                        fullType = "Track";
                        break;
                    case 2:
                        fullType = "Road";
                        break;
                    case 3:
                        fullType = "Trails";
                        break;
                    default:
                        fullType = "No type specified";
                }
                stmt.executeUpdate("INSERT INTO event_list VALUES ('" + name + "','"+ date +"','" + fullType + "')");
                stmt.executeUpdate("CREATE TABLE \"" + name + "\" (id serial, name text, gender char(1), echelon text, trial_time time(2))");
            } 
            else {
                System.err.println("Error: Event name already exists");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
    }

    public serverCallback getDayEvents(String date) throws RemoteException {
        serverCallback cb = new serverCallback();
        try {
            ResultSet rs = stmt.executeQuery("SELECT name, type FROM event_list WHERE date = '" + date + "'");
            while(rs.next()) {
                String callback = "";
                callback = callback.concat(rs.getString("type") + " | " + rs.getString("name"));
                cb.getList().add(callback);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
        return cb;
    }

    public void registerParticipant(String participantName, int gender, int echelon, String eventName) throws RemoteException {
        String fullEchelon = null;
        String fullGender = null;
        try {
            fullEchelon = echelonInterpreter(echelon);
            switch(gender) {
                case 1:
                    fullGender = "M";
                    break;
                case 2:
                    fullGender = "F";
                    break;
            }
            stmt.executeUpdate("INSERT INTO \"" + eventName + "\" (name, gender, echelon) VALUES ('" + participantName + "','"+ fullGender +"','" + fullEchelon + "')");
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
    }

    public serverCallback listParticipants(String name) throws RemoteException {
        serverCallback cb = new serverCallback();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + name + "\"");
            while(rs.next()) {
                String callback = "";
                callback = callback.concat(rs.getString("id") + " | " + rs.getString("name") + " | " + rs.getString("gender") + " | " + rs.getString("echelon"));
                cb.getList().add(callback);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
        return cb;
    }

    public void setParticipantTrialTime(String name, int dorsal, String time) throws RemoteException {
        try {
            stmt.executeUpdate("UPDATE \"" + name + "\" SET trial_time = '" + time + "' WHERE id = " + dorsal);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
    }

    public serverCallback getGeneralScoreboard(String name, int type) throws RemoteException {
        serverCallback cb = new serverCallback();
        ResultSet rs = null;
        try {
            ResultSet rsVerify = stmt.executeQuery("SELECT COUNT(trial_time) FROM \"" + name +"\"");
            rsVerify.next();
            if(rsVerify.getInt(1) > 0){
                switch(type) {
                    case 1:
                        rs = stmt.executeQuery("SELECT * FROM \"" + name + "\" WHERE trial_time IS NOT NULL ORDER BY trial_time ASC");
                        break;
                    case 2:
                        rs = stmt.executeQuery("SELECT * FROM \"" + name + "\" WHERE gender='M' AND trial_time IS NOT NULL ORDER BY trial_time ASC");
                        break;
                    case 3:
                        rs = stmt.executeQuery("SELECT * FROM \"" + name + "\" WHERE gender='F' AND trial_time IS NOT NULL ORDER BY trial_time ASC");
                        break;                    
                }
                int pos = 1;
                while(rs.next()) {
                    String callback = "";
                    callback = callback.concat("[" + pos + "] " + rs.getString("name") + "-" + rs.getString("id") + "   (" + rs.getString("trial_time") + ")");
                    cb.getList().add(callback);
                    pos++;
                }
            }
            else {
                System.err.println("Error: There are no participants with trial time registered");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
        return cb;
    }

    public serverCallback getPodium(String name, int echelon) throws RemoteException {
        serverCallback cb = new serverCallback();
        ResultSet rs = null;
        try {
            ResultSet rsVerify = stmt.executeQuery("SELECT COUNT(trial_time) FROM \"" + name +"\"");
            rsVerify.next();
            if(rsVerify.getInt(1) > 0){
                String fullEchelon = null;
                fullEchelon = echelonInterpreter(echelon);
                rs = stmt.executeQuery("SELECT * FROM \"" + name + "\" WHERE gender='M' AND echelon='" + fullEchelon + "' AND trial_time IS NOT NULL ORDER BY trial_time ASC LIMIT 3");
                int pos = 1;
                while(rs.next()) {
                    String callback = "";
                    callback = callback.concat("[" + pos + "] " + rs.getString("name") + "-" + rs.getString("id") + "   (" + rs.getString("trial_time") + ")");
                    cb.getList().add(callback);
                    pos++;
                }
            }
            else {
                System.err.println("Error: There are no participants with trial time registered");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
        return cb;
    }
}
