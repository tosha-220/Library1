package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;

    @Column(name = "name")
    private String bookTitle;
    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

    @Column(name = "link")
    private String link;

    @Column(name = "hash")
    private String hash;

    @Column(name = "mimeType")
    private String mimeType;


    @Column(name = "login")
    private String login;

    @Column(name = "book_type")
    private String bookType;

//    @ManyToOne
//    @JoinColumn(name = "login", referencedColumnName = "login",
//            insertable = false, updatable = false)
//    private User user;

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookTitle='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", link='" + link + '\'' +
                ", hash='" + hash + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
