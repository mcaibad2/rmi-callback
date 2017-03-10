package client;

import interfaces.CallbackClientInterface;
import interfaces.CallbackServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CallbackClient {

    public static void main(String args[]) throws RemoteException, NotBoundException, MalformedURLException {
        CallbackServerInterface server = (CallbackServerInterface) Naming.lookup("//localhost/motion-sensor");
        System.out.println("Lookup completed ");
        System.out.println("Server said: " + server.sayHello());

        CallbackClientInterface client = new CallbackClientImpl();
        server.registerForCallback(client);
        System.out.println("Registered for callback.");
    }
}
