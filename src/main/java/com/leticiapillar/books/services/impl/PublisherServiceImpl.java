package com.leticiapillar.books.services.impl;

import com.leticiapillar.books.repositories.PublisherRepository;
import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Iterable<PublisherResponse> getPublishers() {
        return Streamable.of(publisherRepository.findAll())
                .map(publisher -> new PublisherResponse(publisher.getId(), publisher.getName()))
                .toList();
    }
}
