package com.netcracker.dao;

import com.netcracker.model.Book;
import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int id);

    Book getBookById(int id);

    List<Book> listBooks();

    List<Book> getBookByTitle(String title);
}
