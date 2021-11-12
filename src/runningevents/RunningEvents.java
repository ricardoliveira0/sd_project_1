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
    public void registerEvent(String name, String date);
    
    public void getDayEvents(String date);
    
    public void registerParticipant(String name, String gender, String echelon);
    
    public void listParticipants(String name);
    
    public void setParticipantTrialTime(String name, int number, String time);
    
    public void getGeneralScoreboard(String name, int type);
    
    public void getPodium(String name, int echelon);
}
