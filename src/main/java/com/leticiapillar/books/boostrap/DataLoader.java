package com.leticiapillar.books.boostrap;

import com.leticiapillar.books.domain.Author;
import com.leticiapillar.books.domain.Book;
import com.leticiapillar.books.domain.Publisher;
import com.leticiapillar.books.repositories.AuthorRepository;
import com.leticiapillar.books.repositories.BookRepository;
import com.leticiapillar.books.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {

        Publisher oReilly = Publisher.builder()
                .name("O'Reilly")
                .active(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();

        Author author1 = Author.builder()
                .firstName("Mark")
                .lastName("Richards")
                .active(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();

        Book book1 = Book.builder()
                .title("Fundamentals of Software Architecture: A Modern Engineering Approach")
                .authors(Set.of(author1))
                .publisher(oReilly)
                .active(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();

        Author author2 = Author.builder()
                .firstName("Nathaniel")
                .lastName("Schutta")
                .active(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();

        Book book2 = Book.builder()
                .title("Fundamentals of Software Engineering: From Coder to Engineer")
                .authors(Set.of(author2))
                .publisher(oReilly)
                .build();

        author1.getBooks().add(book1);
        author2.getBooks().add(book2);

        publisherRepository.save(oReilly);
        authorRepository.save(author1);
        authorRepository.save(author2);
        bookRepository.save(book1);
        bookRepository.save(book2);

        log.info("All books have been loaded successfully");
        log.info("Publisher: {}", publisherRepository.count());
        log.info("Books: {}", bookRepository.count());
        log.info("Authors: {}", authorRepository.count());
    }
}
