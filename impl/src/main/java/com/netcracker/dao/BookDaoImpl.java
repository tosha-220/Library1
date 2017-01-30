package com.netcracker.dao;

import com.netcracker.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
    private SessionFactory sessionFactory;
    private String login;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book successfully added " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully updated " + book);
    }

    @Override
    public void deleteBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (book != null) {
            session.delete(book);
        }
        logger.info("Book successfully deleted " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);

        logger.info("Book successfully load " + book);
        if (book.getBookType().equals("private")) {
            login = SecurityContextHolder.getContext().getAuthentication().getName();
            if (book.getLogin().equals(login)) {
                return book;
            } else {
                return null;
            }
        }
        return book;
    }

    @Override
    public List<Book> listBooks() {
        Session session = sessionFactory.getCurrentSession();
        login = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Book> list = session.createQuery("from Book book where book.bookType='public' or " +
                "(book.bookType='private' and book.login=:login)").setString("login", login).list();
        list.sort(Comparator.comparing(Book::getBookType));
        for (Book book : list) {
            logger.info("Book list: " + book);
        }
        return list;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        login = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Book> list = session.createQuery("from Book book where (book.bookTitle = :name and book.bookType='public') " +
                "or (book.bookTitle = :name and book.login=:login)")
                .setString("name", title).setString("login", login).list();
        for (Book book : list) {
            logger.info("Book list: " + book);
        }
        return list;
    }
}
