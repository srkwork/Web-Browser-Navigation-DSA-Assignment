import java.util.*;
public class Main{
    public static void main(String[] args) {
        BrowserNavigation browser = new BrowserNavigation();
        browser.restoreLastSession();
        Scanner input = new Scanner(System.in);
        
        String commandLine;

        do { 
            
            System.out.println("Enter a command: ");
            commandLine = input.nextLine().trim();


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
                    if(parts.length < 2){
                        System.out.println("Usage: visit <url>");
                    }
                    else{
                        String url = parts[1];
                        browser.visitWebsite(url);
                    }
                    
                    break;
                    
                case "back":
                    browser.goBack();
                    break;
                
                case "forward":
                    browser.goForward();
                    break;

                case "history":
                    System.out.println(browser.showHistory());
                    break;

                case "clear":
                    browser.clearHistory();
                    break;
                
                case "close":
                    browser.closeBrowser();
                    break;
                
                case "exit":
                    browser.closeBrowser();
                    break;
                default:
                    System.out.println("Invalid command.");
            }
            
        } while (!commandLine.equals("exit"));
        input.close();

    }
}