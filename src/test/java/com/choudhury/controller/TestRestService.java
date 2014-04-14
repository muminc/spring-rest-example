package com.choudhury.controller;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class TestRestService extends BaseWebApplicationContextTests {

    @Test
    public void testGetBookAsXML() throws Exception {
        request.setMethod("GET");
        request.addHeader("Accept", "application/xml");
        request.addHeader("Content-Type", "application/xml");
        request.setRequestURI("/book/1");
        request.setContentType("application/xml");
        request.setMethod("GET");

        servlet.service(request, response);
        String result = response.getContentAsString();
        Assert.assertEquals(200, response.getStatus());
        String expectedXML = "<book><id>1</id><author>William Smith</author><title>Advanced Java</title></book>";
        Assert.assertEquals(expectedXML, result);
    }

    @Test
    public void testGetBookAsJSon() throws Exception {
        request.setMethod("GET");
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/xml");
        request.setRequestURI("/book/1");
        request.setContentType("application/xml");
        request.setMethod("GET");

        servlet.service(request, response);
        String result = response.getContentAsString();
        Assert.assertEquals(200, response.getStatus());
        String expectedJSON = "{\"author\":\"William Smith\",\"title\":\"Advanced Java\",\"id\":1}";
        Assert.assertEquals(createTree(expectedJSON), createTree(result));
    }

    private JsonNode createTree(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, JsonNode.class);
    }

    @Test
    public void testAddBookUsingXML() throws Exception {
        request.setMethod("POST");
        request.addHeader("Accept", "application/xml");
        request.setContentType("application/xml");
        request.setRequestURI("/book/add");
        request.addHeader("Content-Type", "application/xml");
        request.setContent("<book><id>-1</id><author>William Smith</author><title>Advanced Java</title></book>".getBytes("utf-8"));
        servlet.service(request, response);
        String result = response.getContentAsString();
        int status = response.getStatus();
        Assert.assertEquals(201, status);
        long expectedId=bookService.getBookCount()-1;
        String expectedXML = "<object><id>"+expectedId+"</id></object>";
        Assert.assertEquals(expectedXML, result);
    }

    @Test
    public void testAddBookUsingJSON() throws Exception {
        request.setMethod("POST");
        request.addHeader("Accept", "application/json");
        request.setContentType("application/json;charset=UTF-8");
        request.setRequestURI("/book/add");
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.setContent("{\"author\":\"William Smith\",\"title\":\"Advanced Java\",\"id\":-1}".getBytes("utf-8"));
        servlet.service(request, response);
        String result = response.getContentAsString();
        int status = response.getStatus();
        Assert.assertEquals(201, status);
        long expectedId=bookService.getBookCount()-1;
        String expectedJSON = "{\"id\":"+expectedId+"}";
        Assert.assertEquals(createTree(expectedJSON), createTree(result));
    }

}
