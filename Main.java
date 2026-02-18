import java.util.*;
public class Main{
    public static void main(String[] args) {
        BrowserNavigation browser = new BrowserNavigation();
        browser.restoreLastSession();
        Scanner input = new Scanner(System.in);

        String command;

        do { 
            
            System.out.println("Enter a command: ");
            command = input.nextLine();

            if(command.startsWith("visit ")){
                // Splitting spaces to extract url
                String[] strArr = command.split("\\s+");

                if(strArr.length < 2){
                    System.out.println("Usage: visit <url>");
                }
                else{
                    String url = strArr[1];
                    browser.visitWebsite(url);
                }
            }
            else{
                switch(command){
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
            }
        } while (!command.equals("exit"));
        input.close();

    }
}