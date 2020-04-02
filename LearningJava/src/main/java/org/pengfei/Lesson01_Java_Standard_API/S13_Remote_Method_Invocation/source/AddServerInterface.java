package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface AddServerInterface extends Remote {
    double add(double d1,double d2) throws RemoteException;
}
