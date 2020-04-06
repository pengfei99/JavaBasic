package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source.bookstore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.function.Predicate;

public class BookStoreSeverImplementation extends UnicastRemoteObject implements BookStoreRemoteInterface {

    private static final long serialVersionUID = 1L;

    private List<MyBook> bookList;
    protected BookStoreSeverImplementation(List<MyBook> bookList) throws RemoteException {
    this.bookList =bookList;

    }

    @Override
    public MyBook findBook(MyBook b) throws RemoteException {
        Predicate<MyBook> predicate=x->x.getIsbn().equals(b.getIsbn());
        return bookList.stream().filter(predicate).findFirst().get();
    }

    @Override
    public List<MyBook> allBooks() throws RemoteException {
        return bookList;
    }
}
