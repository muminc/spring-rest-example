package com.choudhury.controller;

import com.choudhury.service.BookService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public abstract class BaseWebApplicationContextTests {


    // this servlet is going to be instantiated by ourselves
    // so that we can test the servlet behaviour w/o actual web container
    // deployment
    protected DispatcherServlet servlet;


    // we need to get at the context already loaded via the @ContextConfiguration annotation.
    @Resource
    protected ApplicationContext applicationContext;

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected BookRestController controller;
    protected BookService bookService;


    @Before
    public void initDispatcherServlet() throws Exception{
        servlet = new DispatcherServlet() {

            @Override
            protected WebApplicationContext createWebApplicationContext(
                    WebApplicationContext parent) throws BeansException {

                GenericWebApplicationContext gwac = new GenericWebApplicationContext();
                gwac.setParent(applicationContext);
                gwac.refresh();
                return gwac;
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        controller = (BookRestController) applicationContext.getBean("bookRestController");
        bookService = (BookService)applicationContext.getBean("bookService");
        MockServletContext servletContext = new MockServletContext("src/main/webapp", new FileSystemResourceLoader());

        servlet.init(new MockServletConfig(servletContext));
    }
}