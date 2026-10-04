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

        Author author1 = new Author();
        author1.setFirstName("Mark");
        author1.setLastName("Richards");

        Book book1 = new Book();
        book1.setTitle("Fundamentals of Software Architecture: A Modern Engineering Approach");
        book1.setAuthors(Set.of(author1));
        book1.setPublisher(oReilly);

        Author author2 = new Author();
        author2.setFirstName("Nathaniel");
        author2.setLastName("Schutta");

        Book book2 = new Book();
        book2.setTitle("Fundamentals of Software Engineering: From Coder to Engineer");
        book2.setAuthors(Set.of(author2));
        book2.setPublisher(oReilly);

        author1.getBooks().add(book1);
        author2.getBooks().add(book2);

        publisherRepository.save(oReilly);
        authorRepository.save(author1);
        authorRepository.save(author2);
        bookRepository.save(book1);
        bookRepository.save(book2);

        log.info("All books have been loaded successfully");
        log.info("Publisher: {}", publisherRepository.count());
        log.info("Books: {}", bookRepository.findAll());
        log.info("Authors: {}", authorRepository.findAll());
    }
}
