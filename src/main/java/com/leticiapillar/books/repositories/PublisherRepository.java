package com.leticiapillar.books.repositories;

import com.leticiapillar.books.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PublisherRepository extends CrudRepository<Publisher, UUID> {
}
