package com.techprimers.graphql.springbootgrapqlexample.resource;

import com.techprimers.graphql.springbootgrapqlexample.model.Book;
import com.techprimers.graphql.springbootgrapqlexample.service.BookService;
import com.techprimers.graphql.springbootgrapqlexample.service.GraphQLService;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/rest/books")
@RestController
@Slf4j
public class BookResource {

    @Autowired
    GraphQLService graphQLService;

    @Autowired
    private BookService bookService;

    @GetMapping("/health")
    public String greetings() {
        return "Hello, Service is deployed into Azure successfully !!!";
    }

    @PostMapping("/graphql")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        log.info("GraphQL query: {}", query);
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

    @PostMapping("/rest")
    public ResponseEntity<Object> saveBook(@RequestBody Book book) {
        log.info("Save Book request : {}", book);
        Book bookResponse = bookService.saveBook(book);
        log.info("Book response : {}", bookResponse);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
}
