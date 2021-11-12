/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package runningevents;

/**
 *
 * @author ricardo
 */

public interface RunningEvents extends java.rmi.Remote {
    
    public void registerEvent(String name, String date, int type) throws java.rmi.RemoteException;
    
    public serverCallback getDayEvents(String date) throws java.rmi.RemoteException;
    
    public void registerParticipant(String participantName, String gender, String echelon, String eventName) throws java.rmi.RemoteException;
    
    public serverCallback listParticipants(String name) throws java.rmi.RemoteException;
    
    public void setParticipantTrialTime(String name, int number, String time) throws java.rmi.RemoteException;
    
    public serverCallback getGeneralScoreboard(String name, int type) throws java.rmi.RemoteException;
    
    public serverCallback getPodium(String name, int echelon) throws java.rmi.RemoteException;
    
}
