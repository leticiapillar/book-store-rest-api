package com.leticiapillar.books.controllers;

import com.leticiapillar.books.responses.AuthorResponse;
import com.leticiapillar.books.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Iterable<AuthorResponse>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }
}
