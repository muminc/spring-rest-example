package com.choudhury.controller;

import com.choudhury.domain.Book;
import com.choudhury.domain.ObjectWithId;
import com.choudhury.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@Resource(name="restController")
@RequestMapping("/book")
public class RestController {

    @Resource(name="bookService")
	private BookService bookService;

    protected static Logger logger = Logger.getLogger(RestController.class.getName());


    @RequestMapping(value = "/{id}",
								method = RequestMethod.GET,
								headers="Accept=application/xml,application/json")
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Book getBook(@PathVariable("id") Long id) {
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
