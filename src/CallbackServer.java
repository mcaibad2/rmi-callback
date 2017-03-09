import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class CallbackServer {

    public static void main(String args[]) throws RemoteException, MalformedURLException {
        CallbackServerImpl server = new CallbackServerImpl();
        Naming.rebind("//localhost/motion-sensor", server);
        System.out.println("Callback server ready.");
        Timer time = new Timer(); // Instantiate Timer Object
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    server.sendMotionEvents();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000); // Create Repetitively task for every 5 secs
    }
}
