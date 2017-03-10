package server;

import interfaces.CallbackClientInterface;
import interfaces.CallbackServerInterface;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class CallbackServerImpl extends UnicastRemoteObject implements CallbackServerInterface {

    private Vector clients;

    public CallbackServerImpl() throws RemoteException {
        super( );
        clients = new Vector();
    }

    public String sayHello( ) throws java.rmi.RemoteException {
        return("Hello client!");
    }

    @Override
    public void sendMotionEvents() throws RemoteException {
        System.out.println("*** Send motion events ***");
        for (int i = 0; i < clients.size(); i++){
            CallbackClientInterface nextClient = (CallbackClientInterface) clients.elementAt(i);
            nextClient.notifyMe("Here are your motion events");
        }
        System.out.println("*** Finished sending motion events ***");
    }

    public synchronized void registerForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException{
        if (!(clients.contains(callbackClientObject))) {
            clients.addElement(callbackClientObject);
            System.out.println("Registered new client ");
            doCallbacks();
        }
    }

    public synchronized void unregisterForCallback(CallbackClientInterface callbackClientObject) throws RemoteException{
        if (clients.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client ");
        } else {
            System.out.println("unregister: client wasn't registered.");
        }
    }

    protected synchronized void doCallbacks() throws java.rmi.RemoteException{
        System.out.println("**************************************\n" + "Callbacks initiated ---");
        for (int i = 0; i < clients.size(); i++){
            System.out.println("doing "+ i +"-th callback\n");
            // convert the vector object to a callback object
            CallbackClientInterface nextClient = (CallbackClientInterface) clients.elementAt(i);
            // invoke the callback method
            nextClient.notifyMe("Number of registered clients=" +  clients.size());
        }// end for
        System.out.println("********************************\n" + "Server completed callbacks ---");
    }
}