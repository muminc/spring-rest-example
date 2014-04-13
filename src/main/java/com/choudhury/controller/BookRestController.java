package com.choudhury.controller;

import com.choudhury.domain.Book;
import com.choudhury.domain.ObjectWithId;
import com.choudhury.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

@RestController
@Resource(name="bookRestController")
@RequestMapping("/book")
public class BookRestController {

    @Resource(name="bookService")
    private BookService bookService;

    protected static Logger logger = Logger.getLogger(BookRestController.class.getName());


    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            headers="Accept=application/xml,application/json")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable("id") Long id) {
        logger.debug("Provider has received request to get person with id: " + id);
        return bookService.getBook(id);
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            headers="Accept=application/xml,application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectWithId addBook(@RequestBody Book book)
    {
        return new ObjectWithId(bookService.addBook(book));
    }
}
