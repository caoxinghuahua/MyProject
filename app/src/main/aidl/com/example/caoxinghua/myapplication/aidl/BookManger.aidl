// BookManger.aidl
package com.example.caoxinghua.myapplication.aidl;

// Declare any non-default types here with import statements
import com.example.caoxinghua.myapplication.aidl.Book;
interface BookManger {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    List<Book> getBooks();
    Book getBook();
    int getBookCount();
    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    void setBookPrice(in Book book,int price);
    void setBookName(in Book book,String name);
    void addBookIn(in Book book);
    void addBookOut(out Book book);
    void addBookInOut(inout Book book);
}
