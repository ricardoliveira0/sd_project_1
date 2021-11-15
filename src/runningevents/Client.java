/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runningevents;

import java.util.*;

/**
 *
 * @author ricardo
 */

public class Client {
    
    private static String host;
    private static int port, opt, read;
    private static byte[] b = new byte[128];
    
    public static void output(serverCallback cb) {
        clearScreen();
        for (int i = 0; i < cb.getList().size(); i++){
            System.out.println(cb.getList().get(i));
        }
    }
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // print to clear the terminal
    }
    
    public static void main(String[] args) {
        
        if(args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } 
        else {
            System.err.println("Error: Invalid format. Args format: '<host> <port>'");
            System.exit(1);
        }
        
        
        try {
            RunningEvents event = (RunningEvents) java.rmi.Naming.lookup("rmi://" + host + ":" + port + "/runningevents");
            System.out.println("Successfully connected to " + host + ":" + port);
            while(true) {
                System.out.println("");
                System.out.println("==============================================");
                System.out.println("==================== MENU ====================");
                System.out.println("1. Register new event");
                System.out.println("2. Get all events list for a day");
                System.out.println("3. Register new participant");
                System.out.println("4. List participants from an event");
                System.out.println("5. Set participant trial time");
                System.out.println("6. Get general scoreboard");
                System.out.println("7. Get podium");
                System.out.println("8. Exit");
                System.out.println("==============================================");
                Scanner scanner = new Scanner(System.in);
                opt = scanner.nextInt();
                switch(opt) {
                    case 1: // Register new event
                        clearScreen();
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventName = new String(b, 0, read -1);
                        
                        System.out.println("Submit the event date. Format <YYYY-MM-DD>:");
                        read = System.in.read(b);
                        String eventDate = new String(b, 0, read -1);
                        
                        System.out.println("Submit the event type.");
                        System.out.println("1- Track. 2- Road. 3- Trails");
                        int eventType = scanner.nextInt();
                        
                        event.registerEvent(eventName, eventDate, eventType);
                        
                        break;
                    case 2: // Get all events -> day
                        clearScreen();
                        System.out.println("Submit the event date to search. Format <YYYY-MM-DD>:");
                        read = System.in.read(b);
                        String eventSearchDate = new String(b, 0, read -1);
                        
                        output(event.getDayEvents(eventSearchDate));
                        
                        break;
                    case 3: // Register new participant
                        clearScreen();
                        System.out.println("Submit the participant name:");
                        read = System.in.read(b);
                        String participantName = new String(b, 0, read -1);
                        
                        System.out.println("Submit the gender.");
                        System.out.println("1- Male. 2- Female:");
                        int participantGender = scanner.nextInt();
                        
                        System.out.println("Submit the echelon.");
                        System.out.println("1- Juniors. 2- Seniors. 3- Veterans 35. 4- Veterans 40. 5- Veterans 45. 6- Veterans 50. 7- Veterans 55. 8- Veterans 60. 9- Veterans +65:");
                        int participantEchelon = scanner.nextInt();
                        
                        System.out.println("Submit event name to register this participant:");
                        read = System.in.read(b);
                        String eventNameToRegisterParticipant = new String(b, 0, read -1);
                        
                        int dorsal = event.registerParticipant(participantName, participantGender, participantEchelon, eventNameToRegisterParticipant);
                        System.out.println("Successfully registered " + participantName + " with dorsal " + dorsal);
                        break;
                    case 4: // List participants -> event
                        clearScreen();
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventSearchName = new String(b, 0, read -1);
                        
                        output(event.listParticipants(eventSearchName));
                        
                        break;
                    case 5: // Set participant trial time
                        clearScreen();
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventNameForTrialTime = new String(b, 0, read -1);
                        
                        System.out.println("Submit the participant dorsal:");
                        int participantNoForTrialTime = scanner.nextInt();
                        
                        System.out.println("Submit the participant trial time:");
                        read = System.in.read(b);
                        String participantTrialTime = new String(b, 0, read -1);
                        
                        event.setParticipantTrialTime(eventNameForTrialTime, participantNoForTrialTime, participantTrialTime);
                        
                        break;
                    case 6: // Get general scoreboard
                        clearScreen();
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventNameForScoreboard = new String(b, 0, read -1);
                        
                        System.out.println("Submit scoreboard type.");
                        System.out.println("1- Absolut. 2- Male. 3- Female:");
                        int scoreboardType = scanner.nextInt();
                        
                        output(event.getGeneralScoreboard(eventNameForScoreboard, scoreboardType));
                        
                        break;
                    case 7: // Get podium
                        clearScreen();
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventNameForPodium = new String(b, 0, read -1);
                        
                        System.out.println("Submit echelon.");
                        System.out.println("1- Juniors (18-19). 2- Seniors (20-34). 3- Veterans 35 (35-39). 4- Veterans 40 (40-44). 5- Veterans 45 (45-49). 6- Veterans 50 (50-54). 7- Veterans 55 (55-59). 8- Veterans 60 (60-64). 9- Veterans 65+ (+65):");
                        int echelon = scanner.nextInt();
                        
                        output(event.getPodium(eventNameForPodium, echelon));
                        
                        break;
                    case 8: // Exit
                        System.exit(0);
                        break;
                    default: 
                        System.err.println("Invalid option.");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error: Could not connect to specified host:port");
        }
    }
}
