package com.leticiapillar.books.services;

import com.leticiapillar.books.responses.AuthorResponse;

public interface AuthorService {
    Iterable<AuthorResponse> getAuthors();
}
