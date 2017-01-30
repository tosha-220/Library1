package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.service.BookService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class BookController {

    private BookService bookService;
    private String name;
    private String hashCode;
    @Value("${root.path}")
    private String rootPath;

    @Autowired
    private ServletContext context;

    @Autowired
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        System.out.println("asdfadfacfsad");
        model.addAttribute("listBooks", this.bookService.listBooks());
        return "books";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Book book) {
        this.bookService.updateBook(book);
        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("list", this.bookService.listBooks());
        return "edit";
    }

    @RequestMapping("/search")
    public String getDataByName(@RequestParam("bookTitle") String bookTitle, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.getBookByName(bookTitle));
        return "books";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute("book") Book book,
                             @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            try (InputStream inputStream = file.getInputStream()) {
                hashCode = DigestUtils.md5Hex(inputStream);
                name = hashCode + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                book.setMimeType(context.getMimeType(name));
                book.setLink(rootPath + name);
                book.setHash(hashCode);
                book.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
                this.bookService.addBook(book);
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/books";
            }

            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rootPath + name)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/books";

        } else {
            return "Fault " + book.getBookTitle() + " file is empty";
        }
    }

    @RequestMapping(value = "/download/{id_book}", method = RequestMethod.GET)
    public String downloadFile(@PathVariable("id_book") int id,
                               HttpServletResponse response) throws Throwable {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        File file = new File(book.getLink());
        try (OutputStream outStream = response.getOutputStream(); FileInputStream inputStream = new FileInputStream(file)) {
            response.setContentType(book.getMimeType());
            response.addHeader("Content-Disposition",
                    "inline; filename=\"" + book.getBookTitle() + file.getName().substring(file.getName().lastIndexOf(".")) + "\"");
            response.setContentLength((int) file.length());
            response.getOutputStream();
            IOUtils.copy(inputStream, outStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/books";
    }
}