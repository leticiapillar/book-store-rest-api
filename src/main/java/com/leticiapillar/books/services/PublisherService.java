package com.leticiapillar.books.services;

import com.leticiapillar.books.requests.PublisherRequest;
import com.leticiapillar.books.responses.PublisherResponse;

import java.util.Optional;
import java.util.UUID;

public interface PublisherService {
    Iterable<PublisherResponse> findAll();
    Optional<PublisherResponse> findById(UUID id);
    UUID create(PublisherRequest publisherRequest);
    void update(UUID id, PublisherRequest publisherRequest);
    void delete(UUID id);
}
