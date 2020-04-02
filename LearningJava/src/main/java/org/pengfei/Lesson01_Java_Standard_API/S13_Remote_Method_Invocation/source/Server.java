package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args){
        try {
            AddServerImpl addServer=new AddServerImpl();
            Naming.rebind("AddServer",addServer);
            System.out.println("Server Starting:");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
