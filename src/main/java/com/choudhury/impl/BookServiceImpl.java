package com.choudhury.impl;

import com.choudhury.domain.Book;
import com.choudhury.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService{

     protected static Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

    public BookServiceImpl()
    {
        init();
    }

    // In-memory list
	private List<Book> books = new ArrayList<Book>();

    private void init()
    {
        addBook("John Smith","Spring Framework intro");
        addBook("William Smith","Advanced Java");
    }

    public Book getBook(long id) {
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
      int idTodSet=books.size()+1;
      book.setId(idTodSet);
      books.add(book);
      return idTodSet;
    }

    public long addBook(String author, String title) {
        Book book = new Book(books.size() + 1, author, title);
        return addBook(book);
    }

    public long getBookCount() {
        return books.size();
    }
}


