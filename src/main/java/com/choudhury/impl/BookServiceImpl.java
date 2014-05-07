package com.choudhury.impl;

import com.choudhury.domain.Book;
import com.choudhury.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Named;

@Named("bookService")
public class BookServiceImpl implements BookService{

    private static Logger logger= LoggerFactory.getLogger(BookServiceImpl.class);
    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public BookServiceImpl()
    {
        init();
    }

    // In-memory list
    private List<Book> books = new ArrayList<>();

    private void init()
    {
        addBook("John Smith","Spring Framework intro");
        addBook("William Smith","Advanced Java");
    }

    public Book getBook(long id) {
        logger.info("Retrieving id {}",id);
        for (Book book : books) {
            if (book.getId()==id)
            {
                return book;
            }
        }
        return null;
    }

    public long addBook(Book book)
    {
        int idTodSet=atomicInteger.getAndIncrement();
        book.setId(idTodSet);
        books.add(book);
        return idTodSet;
    }

    public long addBook(String author, String title) {
        Book book = new Book(-1, author, title);
        return addBook(book);
    }

    public long getBookCount() {
        return books.size();
    }
}


