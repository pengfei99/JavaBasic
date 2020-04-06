package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source.bookstore;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookStoreRemoteInterface extends Remote {
    MyBook findBook(MyBook b) throws RemoteException;
    List<MyBook> allBooks() throws RemoteException;
}
