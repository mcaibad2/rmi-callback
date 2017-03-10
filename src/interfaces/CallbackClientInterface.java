package interfaces;

import java.rmi.Remote;

public interface CallbackClientInterface extends Remote {
    String notifyMe(String message) throws java.rmi.RemoteException;
}