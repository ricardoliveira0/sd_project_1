/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runningevents;

import java.util.Scanner;

/**
 *
 * @author ricardo
 */

public class Client {
    
    private static String host;
    private static int port, opt, read;
    private static byte[] b = new byte[128];
    
    public static void main(String[] args) {
        
        if(args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            System.out.println("HOST:"+host+" | PORT:"+port);
        } 
        else {
            System.err.println("Error: Invalid format. Args format: '<host> <port>'");
            System.exit(1);
        }
        
        
        try {
            RunningEvents event = (RunningEvents) java.rmi.Naming.lookup("rmi://" + host + ":" + port + "/runningevents");
            while(true) {
                System.out.println("Successfully connected to " + host + ":" + port);
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
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventName = new String(b, 0, read -1);
                        
                        System.out.println("Submit the event date. Format <YY-MM-DD>:");
                        read = System.in.read(b);
                        String eventDate = new String(b, 0, read -1);
                        
                        break;
                    case 2: // Get all events -> day
                        System.out.println("Submit the event date to search. Format <YY-MM-DD>:");
                        read = System.in.read(b);
                        String eventSearchDate = new String(b, 0, read -1);
                        
                        break;
                    case 3: // Register new participant
                        System.out.println("Submit the participant name:");
                        read = System.in.read(b);
                        String participantName = new String(b, 0, read -1);
                        
                        System.out.println("Submit the gender:");
                        read = System.in.read(b);
                        String participantGender = new String(b, 0, read -1);
                        
                        System.out.println("Submit the echelon:");
                        read = System.in.read(b);
                        String participantEchelon = new String(b, 0, read -1);
                        
                        break;
                    case 4: // List participants -> event
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventSearchName = new String(b, 0, read -1);
                        
                        break;
                    case 5: // Set participant trial time
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventNameForTrialTime = new String(b, 0, read -1);
                        
                        System.out.println("Submit the participant number:");
                        read = System.in.read(b);
                        String participantNoForTrialTime = new String(b, 0, read -1);
                        
                        System.out.println("Submit the participant trial time:");
                        read = System.in.read(b);
                        String participantTrialTime = new String(b, 0, read -1);
                        
                        break;
                    case 6: // Get general scoreboard
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventNameForScoreboard = new String(b, 0, read -1);
                        
                        System.out.println("Submit scoreboard type.");
                        System.out.println("1- Absolut. 2- Male. 3- Female:");
                        int scoreboardType = scanner.nextInt();
                        switch(scoreboardType) {
                            case 1: // Absolut
                                
                                break;
                            case 2: // Male
                                
                                break;
                            case 3: // Female
                                
                                break;
                        }
                        
                        break;
                    case 7: // Get podium
                        System.out.println("Submit the event name:");
                        read = System.in.read(b);
                        String eventNameForPodium = new String(b, 0, read -1);
                        
                        System.out.println("Submit echelon.");
                        System.out.println("1- Juniors. 2- Seniors. 3- Veterans I. 4- Veterans II. 5- Veterans III. 6- Veterans IV. 7- Veterans V. 8- Veterans VI. 9- Veterans VII:");
                        int echelon = scanner.nextInt();
                        switch(echelon) {
                            case 1: // Juniors (18-19)
                                
                                break;
                            case 2: // Seniors (20-34)
                                
                                break;
                            case 3: // Veterans I (35-39)
                                
                                break;
                            case 4: // Veterans II (40-44)
                                
                                break;
                            case 5: // Veterans III (45-49)
                                
                                break;
                            case 6: // Veterans IV (50-54)
                                
                                break;
                            case 7: // Veterans V (55-59)
                                
                                break;
                            case 8: // Veterans VI (60-64)
                                
                                break;
                            case 9: // Veterans VII (+65)
                                
                                break;  
                        }
                        
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
