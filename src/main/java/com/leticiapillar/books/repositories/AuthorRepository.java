package com.leticiapillar.books.repositories;

import com.leticiapillar.books.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
