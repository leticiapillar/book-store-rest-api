package com.leticiapillar.books.services.impl;

import com.leticiapillar.books.repositories.BookRepository;
import com.leticiapillar.books.responses.BookResponse;
import com.leticiapillar.books.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Iterable<BookResponse> getBooks() {
        return Streamable.of(bookRepository.findAll())
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getIsbn()))
                .toList();
    }
}
