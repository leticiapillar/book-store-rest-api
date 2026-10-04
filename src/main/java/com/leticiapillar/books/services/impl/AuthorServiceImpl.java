package com.leticiapillar.books.services.impl;

import com.leticiapillar.books.domain.Author;
import com.leticiapillar.books.domain.Publisher;
import com.leticiapillar.books.repositories.AuthorRepository;
import com.leticiapillar.books.requests.AuthorRequest;
import com.leticiapillar.books.responses.AuthorResponse;
import com.leticiapillar.books.responses.BookResponse;
import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Iterable<AuthorResponse> findAll() {
        return Streamable.of(authorRepository.findAll())
                .map(author -> new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(),
                        author.getActive()))
                .toList();
    }

    @Override
    public Optional<AuthorResponse> findById(UUID id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(),
                        author.getActive()));
    }

    @Override
    public UUID create(AuthorRequest authorRequest) {
        Author author = Author.builder()
                .firstName(authorRequest.firstName())
                .lastName(authorRequest.lastName())
                .active(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();
        return authorRepository.save(author).getId();
    }

    @Override
    @Transactional
    public void update(UUID id, AuthorRequest authorRequest) {
        Author author = getAuthorOrThrow(id);
        author.setFirstName(authorRequest.firstName());
        author.setLastName(authorRequest.lastName());
        author.setUpdatedAt(LocalDateTime.now());
        authorRepository.save(author);
    }

    @Override
    public void delete(UUID id) {
        Author author = getAuthorOrThrow(id);
        author.setActive(Boolean.FALSE);
        author.setUpdatedAt(LocalDateTime.now());
        authorRepository.save(author);
    }

    private Author getAuthorOrThrow(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found: " + id));
    }

}
