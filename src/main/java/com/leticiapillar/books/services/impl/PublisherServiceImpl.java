package com.leticiapillar.books.services.impl;

import com.leticiapillar.books.domain.Publisher;
import com.leticiapillar.books.repositories.PublisherRepository;
import com.leticiapillar.books.requests.PublisherRequest;
import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Iterable<PublisherResponse> findAll() {
        return Streamable.of(publisherRepository.findAll())
                .map(publisher -> new PublisherResponse(publisher.getId(), publisher.getName(), publisher.getActive()))
                .toList();
    }

    @Override
    public Optional<PublisherResponse> findById(UUID id) {
        return publisherRepository.findById(id)
                .map(publisher -> new PublisherResponse(publisher.getId(), publisher.getName(), publisher.getActive()));
    }

    @Override
    public UUID create(PublisherRequest publisherRequest) {
        Publisher publisher = Publisher.builder()
                .name(publisherRequest.name())
                .active(Boolean.TRUE)
                .build();
        return publisherRepository.save(publisher).getId();
    }

    @Override
    @Transactional
    public void update(UUID id, PublisherRequest publisherRequest) {
        Publisher publisher = getPublisherOrThrow(id);
        publisher.setName(publisherRequest.name());
        publisher.setUpdatedAt(LocalDateTime.now());
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Publisher publisher = getPublisherOrThrow(id);
        publisher.setActive(Boolean.FALSE);
        publisher.setUpdatedAt(LocalDateTime.now());
        publisherRepository.save(publisher);
    }

    private Publisher getPublisherOrThrow(UUID id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found: " + id));
    }

}
