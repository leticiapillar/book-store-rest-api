package com.leticiapillar.books.services;

import com.leticiapillar.books.requests.AuthorRequest;
import com.leticiapillar.books.responses.AuthorResponse;

import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    Iterable<AuthorResponse> findAll();
    Optional<AuthorResponse> findById(UUID id);
    UUID create(AuthorRequest authorRequest);
    void update(UUID id, AuthorRequest authorRequestRequest);
    void delete(UUID id);
}
