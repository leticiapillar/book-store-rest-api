package com.leticiapillar.books.services.impl;

import com.leticiapillar.books.domain.Author;
import com.leticiapillar.books.repositories.AuthorRepository;
import com.leticiapillar.books.responses.AuthorResponse;
import com.leticiapillar.books.responses.BookResponse;
import com.leticiapillar.books.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Iterable<AuthorResponse> getAuthors() {
        return Streamable.of(authorRepository.findAll())
                .map(author -> new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName()))
                .toList();
    }
}
