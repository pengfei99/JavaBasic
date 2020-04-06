package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source.bookstore;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

public class BookSever {
    public  static void main(String args[]){
        List<MyBook> bookList = new ArrayList<>();
        bookList.add(new MyBook("Head First Java, 2nd Edition", "978-0596009205", 31.41));
        bookList.add(new MyBook("Java In A Nutshell", "978-0596007737", 10.90));
        bookList.add(new MyBook("Java: The Complete Reference", "978-0071808552", 40.18));
        bookList.add(new MyBook("Head First Servlets and JSP", "978-0596516680", 35.41));
        bookList.add(new MyBook("Java Puzzlers: Traps, Pitfalls, and Corner Cases", "978-0321336781", 39.99));


        try {
           // LocateRegistry.createRegistry();
            BookStoreSeverImplementation bookServer=new BookStoreSeverImplementation(bookList);

            Naming.rebind("//localhost/MyBookstore",bookServer);

            System.out.println("Sever Ready");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
