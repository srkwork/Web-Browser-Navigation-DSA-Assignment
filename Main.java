import java.util.*;
public class Main{
    public static void main(String[] args) {


        // Creating the main browser controller
        BrowserNavigation browser = new BrowserNavigation();

        // Attempt to restore the previous session from session_data.txt (if file is not empty)
        browser.restoreLastSession();
        Scanner input = new Scanner(System.in);
        
        String commandLine;

        // do-while loop to keep prompting until the user types "exit"
        do { 
            
            // Getting the command
            System.out.println("Enter a command: ");
            commandLine = input.nextLine().trim();

            // Ensuring that the command input should not be empty
            if(commandLine.isEmpty()){
                System.out.println("Invalid command");
                continue;
            }
            
            // Splitting all spaces
            String[] parts = commandLine.split("\\s+");
            
            // Getting the command from the parts array
            String command = parts[0];

            switch(command){

                case "visit":

                    // Visiting requires a URL argument (e.g, visit google.com)
                    if(parts.length < 2){
                        System.out.println("Usage: visit <url>");
                    }
                    else{
                        String url = parts[1];
                        browser.visitWebsite(url);
                    }                    
                    break;

                case "back":

                // Move to the previous page if possible
                    browser.goBack();
                    break;
                
                case "forward":

                    // Move to the next page if possible
                    browser.goForward();
                    break;

                case "history":

                    // Display browsing history from the history queue (oldest -> newest)
                    System.out.println(browser.showHistory());
                    break;

                case "clear":

                    // Clears browsing history
                    browser.clearHistory();
                    break;
                
                case "close":

                    // Persists to the current session (CURRENT/BACK/FORWARD/HISTORY) to session_data.txt
                    browser.closeBrowser();
                    break;
                
                case "exit":

                    // Save session before quitting so it can be restored
                    browser.closeBrowser();
                    break;
                default:
                    // Any unrecognized command will display this message
                    System.out.println("Invalid command.");
            }
            
        } while (!commandLine.equals("exit"));

        // Close input
        input.close();

    }
}