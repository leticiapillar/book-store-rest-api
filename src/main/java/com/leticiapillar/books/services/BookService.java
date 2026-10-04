package com.leticiapillar.books.services;

import com.leticiapillar.books.requests.BookRequest;
import com.leticiapillar.books.responses.BookResponse;

import java.util.Optional;
import java.util.UUID;

public interface BookService {
    Iterable<BookResponse> findAll();
    Optional<BookResponse> findById(UUID id);
    UUID create(BookRequest bookRequest);
    void update(UUID id, BookRequest bookRequest);
    void delete(UUID id);
}
