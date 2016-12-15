package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.service.BookService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${root.path}")
    private String rootPath;

    @Autowired
    ServletContext context;

    @Autowired
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
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

    @RequestMapping("/bookdata/{id}")
    public String bookDate(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        return "bookdata";
    }

    @RequestMapping("/search")
    public String getDateByName(@RequestParam("bookTitle") String bookTitle, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.getBookByName(bookTitle));
        return "books";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String ks(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public /*@ResponseBody*/ String fileUpload(@ModelAttribute("book") Book book,
                                               @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            int hashCode = book.hashCode();
            name = hashCode + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            book.setMimeType(context.getMimeType(name));
            book.setLink(rootPath + name);
            book.setHash(hashCode);
            this.bookService.addBook(book);
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rootPath + name)));
            stream.write(bytes);
            stream.close();
            return "redirect:/books";

        } else {
            return "Fault " + book.getBookTitle() + " file is empty";
        }
    }

    @RequestMapping(value = "/download/{hash}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("hash") int hash,
                             HttpServletResponse response) throws Throwable {

        Book book = bookService.getBookByHash(hash);
        File file = new File(book.getLink());
        FileInputStream inputStream;
        OutputStream outStream;
        inputStream = new FileInputStream(file);
        response.setContentType(book.getMimeType());
        response.addHeader("Content-Disposition",
                "inline; filename=\"" + book.getBookTitle() + file.getName().substring(file.getName().lastIndexOf(".")) + "\"");
        response.setContentLength((int) file.length());
        response.getOutputStream();
        outStream = response.getOutputStream();
        IOUtils.copy(inputStream, outStream);
        response.flushBuffer();
    }
}