package com.leticiapillar.books.services.impl;

import com.leticiapillar.books.domain.Author;
import com.leticiapillar.books.domain.Book;
import com.leticiapillar.books.domain.Publisher;
import com.leticiapillar.books.repositories.AuthorRepository;
import com.leticiapillar.books.repositories.BookRepository;
import com.leticiapillar.books.repositories.PublisherRepository;
import com.leticiapillar.books.requests.BookRequest;
import com.leticiapillar.books.responses.AuthorResponse;
import com.leticiapillar.books.responses.BookResponse;
import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.BookService;
import com.leticiapillar.books.services.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public Iterable<BookResponse> findAll() {
        return Streamable.of(bookRepository.findAll())
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getIsbn(), book.getActive()))
                .toList();
    }

    @Override
    public Optional<BookResponse> findById(UUID id) {
        return bookRepository.findById(id)
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getIsbn(), book.getActive()));
    }

    @Override
    public UUID create(BookRequest bookRequest) {
        Stream<Author> authorStream = bookRequest.authors().stream().map(author -> authorRepository.findById(author.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found: " + id)));

        Publisher publisher = publisherRepository.findById(bookRequest.publisher().id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found: " + id));

        Book book = Book.builder()
                .title(bookRequest.title())
                .isbn(bookRequest.isbn())
                .active(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .authors(authorStream.collect(Collectors.toSet()))
                .publisher(publisher)
                .build();

        return bookRepository.save(book).getId();
    }

    @Override
    public void update(UUID id, BookRequest bookRequest) {
        Book book = getBookOrThrow(id);
        book.setTitle(bookRequest.title());
        book.setIsbn(bookRequest.isbn());
        bookRepository.save(book);
    }

    @Override
    public void delete(UUID id) {
        Book book = getBookOrThrow(id);
        book.setActive(Boolean.FALSE);
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
    }

    private Book getBookOrThrow(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + id));
    }

}
