import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BrowserNavigation {
    private String currentPage;
    private BrowserStack<String> backStack;
    private BrowserStack<String> forwardStack;
    private BrowserQueue<String> historyQueue;

    public BrowserNavigation(){
        currentPage = null;
        backStack = new BrowserStack<>();
        forwardStack = new BrowserStack<>();
        historyQueue = new BrowserQueue<>();
    }

    // Method to visit website
    public void visitWebsite(String url){

        if(url == null) return;

        // Pushing the current page to BACK before changing pages
        if(currentPage != null) backStack.push(currentPage);

        // Visiting a new page clears forward history
        forwardStack.clear();
        
        // Updating current page and history
        currentPage = url;
        System.out.println("\nNow at " + currentPage + "\n");
        historyQueue.enqueue(url);
    }

    // Method to go to the previous page
    public String goBack(){
        if(backStack.isEmpty()) return currentPage; // No change
        
        // CurrentPage becomes part of forward history
        if(currentPage != null) forwardStack.push(currentPage);

        // Popping from backward and making it the new current
        currentPage = backStack.pop();
        System.out.println("\nNow at " + currentPage + "\n");
        return currentPage;
    }

    // Method to move to the next page
    public String goForward(){
        if(forwardStack.isEmpty()) return currentPage; // No change

        // CurrentPage becomes part of backward history
        if(currentPage != null) backStack.push(currentPage);

        // Popping from forward and making it the new current
        currentPage = forwardStack.pop();
        System.out.println("\nNow at " + currentPage + "\n");
        return currentPage;
    }

    // Method to display browsing history (if it exists)
    public String showHistory(){
        if(historyQueue.isEmpty()) return "No browsing history available";


        StringBuilder history = new StringBuilder();
        
        // Storing the urls visited
        for(String str: historyQueue){
            history.append(str).append("\n");
        }
        return history.toString();
    }

    // Method to delete browsing history
    public void clearHistory(){
        historyQueue.clear();
    }

    // Method to close browser
    public void closeBrowser(){
        Path filePath = Path.of("session_data.txt");

        try {
            StringBuilder sb = new StringBuilder();

            // Save current page
            sb.append("CURRENT\n");
            sb.append(currentPage == null ? "null" : currentPage).append("\n");

            // Save back stack
            sb.append("BACK\n");
            for(String page: backStack){
                sb.append(page).append("\n");
            }
            
            // Save forward stack
            sb.append("FORWARD\n");
            for(String page: forwardStack){
                sb.append(page).append("\n");
            }

            // Save history queue
            sb.append("HISTORY\n");
            for(String page: historyQueue){
                sb.append(page).append("\n");
            }

            // Writing data to the filePath (session_data.txt)
            Files.writeString(filePath, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to restore previous session in browser
    public void restoreLastSession(){
        File file = new File("session_data.txt");
        if(!file.exists()) return;

        // Start clean
        backStack.clear();
        forwardStack.clear();
        historyQueue.clear();
        currentPage = null;

        // Temporary buffers for restoration
        List<String> backLines = new ArrayList<>();
        List<String> forwardLines = new ArrayList<>();
        List<String> historyLines = new ArrayList<>();

        try{

            Scanner fileScnr = new Scanner(file);
            String section = "";

            // Reading file contents
            while(fileScnr.hasNextLine()){
                String line = fileScnr.nextLine();

                if(line.equals("CURRENT")){
                    section = "CURRENT";
                    
                    // Next line is the actual current page
                    if(fileScnr.hasNextLine()){
                        String page = fileScnr.nextLine();
                        currentPage = page.equals("null") ? null : page;
                    }
                    continue;
                }

                if(line.equals("BACK")){
                    section = "BACK";
                    continue;
                }
                if(line.equals("FORWARD")){
                    section = "FORWARD";
                    continue;
                }

                if(line.equals("HISTORY")){
                    section = "HISTORY";
                    continue;
                }

                // Data lines
                if(section.equals("BACK")) backLines.add(line);
                else if(section.equals("FORWARD")) forwardLines.add(line);
                else if(section.equals("HISTORY")) historyLines.add(line);
                

            }
            // Pushing the stacks in reverse so the original top is restored as the top due to LIFO.
            for(int i = backLines.size() - 1; i >= 0; i--){
                backStack.push(backLines.get(i));
            }
            for(int i = forwardLines.size() - 1; i >= 0; i--){
                forwardStack.push(forwardLines.get(i));
            }

            // Pushing the queue in order so the original top is restored as the top due to FIFO.
            for(int i = 0; i < historyLines.size(); i++){
                historyQueue.enqueue(historyLines.get(i));
            }
            fileScnr.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
