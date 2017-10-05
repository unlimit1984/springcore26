/**
 * Created by Vladimir_Vysokomorny on 03-Oct-17.
 */
public class App {

    private Client client;
    private ConsoleEventLogger eventLogger;


    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args){

        App app = new App();
        app.client = new Client("1", "John Smith");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event from user 1");
    }

}
