package com.example.caoxinghua.myapplication.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class AidlService extends Service {
    private List<Book> books=new ArrayList<>();
    public AidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Book book=new Book();
        book.setName("android 大全");
        book.setPrice(30);
        books.add(book);
    }
    BookManger.Stub binder= new BookManger.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this){
                if(books!=null){
                    return books;
                }
                return new ArrayList<>();
            }

        }

        @Override
        public Book getBook() throws RemoteException {
            synchronized (this){
                if(books!=null&&books.size()>0){
                    return books.get(0);
                }
                Book book=new Book();
                book.setName("think java");
                book.setPrice(55);
                return book;
            }
        }

        @Override
        public int getBookCount() throws RemoteException {
            synchronized (this){
                if(books!=null){
                    return  books.size();
                }
                return 0;
            }
        }

        @Override
        public void setBookPrice(Book book, int price) throws RemoteException {

        }

        @Override
        public void setBookName(Book book, String name) throws RemoteException {

        }

        @Override
        public void addBookIn(Book book) throws RemoteException {
            synchronized (this){
                if(books!=null&&book!=null){
                    Log.i("xxx","book in name:"+book.getName() +" price:"+book.getPrice());
                    books.add(book);
                }
            }
        }

        @Override
        public void addBookOut(Book book) throws RemoteException {
            synchronized (this){
                if(books!=null&&book!=null){
                    Log.i("xxx","book out name:"+book.getName() +" price:"+book.getPrice());
                    books.add(book);
                }
            }
        }

        @Override
        public void addBookInOut(Book book) throws RemoteException {
            synchronized (this){
                if(books!=null&&book!=null){
                    Log.i("xxx","book  inout name:"+book.getName() +" price:"+book.getPrice());
                    books.add(book);
                }
            }
        }
    };
}
