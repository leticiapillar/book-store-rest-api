package com.leticiapillar.books.controllers;

import com.leticiapillar.books.responses.BookResponse;
import com.leticiapillar.books.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Iterable<BookResponse>> getAuthors() {
        return ResponseEntity.ok(bookService.getBooks());
    }
}
