package com.netcracker.dao;

import com.netcracker.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
    private SessionFactory sessionFactory;

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
        Book book = (Book) session.load(Book.class, new Integer(id));
        if (book != null) {
            session.delete(book);
        }
        logger.info("Book successfully deleted " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully load " + book);
        return book;
    }

    @Override
    public Book getBookByHash(int hash) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book book where book.hash=:hash").setInteger("hash", hash).list();
        logger.info("Book list: " + bookList.get(0));
        return bookList.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = session.createQuery("from Book").list();
        for (Book book : list) {
            logger.info("Book list: " + book);
        }
        return list;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = session.createQuery("from Book book where book.bookTitle = :name").setString("name", title).list();
        for (Book book : list) {
            logger.info("Book list: " + book);
        }
        return list;
    }
}
