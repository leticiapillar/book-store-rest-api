package com.leticiapillar.books.services;

import com.leticiapillar.books.responses.PublisherResponse;

public interface PublisherService {
    Iterable<PublisherResponse> getPublishers();
}
