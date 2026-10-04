package com.leticiapillar.books.controllers;

import com.leticiapillar.books.domain.Author;
import com.leticiapillar.books.requests.AuthorRequest;
import com.leticiapillar.books.requests.PublisherRequest;
import com.leticiapillar.books.responses.AuthorResponse;
import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Iterable<AuthorResponse>> getAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthors(@PathVariable UUID id) {
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
        UUID uuid = authorService.create(authorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(uuid).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable UUID id,
                                                       @RequestBody AuthorRequest authorRequest) {
        authorService.update(id, authorRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponse> deletePublisher(@PathVariable UUID id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
