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
                stmt.executeUpdate("CREATE TABLE \"" + name + "\" (id serial, name text, gender char(1), echelon text, trial_time time)");
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
                String eventName = rs.getString("name");
                String eventType = rs.getString("type");
                eventName = eventName.concat(" | ");
                eventName = eventName.concat(eventType);
                cb.getList().add(eventName);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
        return cb;
    }

    public void registerParticipant(String participantName, String gender, int echelon, String eventName) throws RemoteException {
        String fullEchelon = null;
        try {
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
            stmt.executeUpdate("INSERT INTO \"" + eventName + "\" (name, gender, echelon) VALUES ('" + participantName + "','"+ gender +"','" + fullEchelon + "')");
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
                String participantID = rs.getString("id");
                String participantName = rs.getString("name");
                String participantGender = rs.getString("gender");
                String participantEchelon = rs.getString("echelon");
                participantID = participantID.concat(" | ");
                participantID = participantID.concat(participantName);
                participantID = participantID.concat(" | ");
                participantID = participantID.concat(participantGender);
                participantID = participantID.concat(" | ");
                participantID = participantID.concat(participantEchelon);
                cb.getList().add(participantID);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
        return cb;
    }

    public void setParticipantTrialTime(String name, int number, String time) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public serverCallback getGeneralScoreboard(String name, int type) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public serverCallback getPodium(String name, int echelon) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
