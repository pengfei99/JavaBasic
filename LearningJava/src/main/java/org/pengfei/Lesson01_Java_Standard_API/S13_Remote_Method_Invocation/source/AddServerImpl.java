package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddServerImpl extends UnicastRemoteObject implements AddServerInterface {

    public AddServerImpl() throws RemoteException{

    }

    @Override
    public double add(double d1, double d2) throws RemoteException {
        return d1+d2;
    }
}
