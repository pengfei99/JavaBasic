package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args){
        String serverIp="127.0.0.1";
        double arg1=1.0, arg2=2.0;

        String addServerURL="rmi://"+serverIp+"/AddServer";

        try {
            //The program then invokes the lookup( )
            //method of the Naming class. This method accepts one argument, the rmi URL,
            //and returns a reference to an object of type AddServerInterface. All remote method
            //invocations can then be directed to this object.
            AddServerInterface addServerInterface= (AddServerInterface) Naming.lookup(addServerURL);
            System.out.println("Value of arg1: "+arg1+", arg2: "+arg2);
            double sum=addServerInterface.add(arg1,arg2);
            System.out.println("The sum is: "+sum);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
