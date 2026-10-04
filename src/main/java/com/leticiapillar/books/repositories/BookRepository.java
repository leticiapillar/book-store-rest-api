package com.leticiapillar.books.repositories;

import com.leticiapillar.books.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
}
