package com.leticiapillar.books.services;

import com.leticiapillar.books.responses.BookResponse;

public interface BookService {
    Iterable<BookResponse> getBooks();
}
