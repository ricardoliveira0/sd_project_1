/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runningevents;

import java.lang.*;
import java.sql.Statement;
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

    public void registerEvent(String name, String date) throws RemoteException {

        try { 
            stmt.executeUpdate("INSERT INTO event_list VALUES ('" + name + "','"+ date +"')");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: Query was not executed");
        }
    }

    public serverCallback getDayEvents(String date) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void registerParticipant(String name, String gender, String echelon) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public serverCallback listParticipants(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
