package com.galvanize.tmo.paspringstarter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    @GetMapping("/health")
    public void health() {
    }

    ArrayList<Book> booklist = new ArrayList<Book>(Arrays.asList());

    @GetMapping(value = "/api/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks() {
        System.out.println("getBooks");
        List<Book> sortedBookList = booklist.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
        return ResponseEntity.ok().body(sortedBookList);
    }

    @DeleteMapping(value = "/api/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deleteAllBooks() {
        booklist.clear();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping(value = "/api/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> updatePerson(@RequestBody Book book) {
        Book newbook = new Book(booklist.size() + 1, book.getAuthor(), book.getTitle(), book.getYearPublished());
        booklist.add(newbook);
        return ResponseEntity.status(HttpStatus.CREATED).body(newbook);
    }
}
