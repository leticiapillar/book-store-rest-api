package com.leticiapillar.books.controllers;

import com.leticiapillar.books.requests.PublisherRequest;
import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Iterable<PublisherResponse>> getPublishers() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getPublishers(@PathVariable UUID id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> createPublisher(@RequestBody PublisherRequest publisherRequest) {
        UUID uuid = publisherService.create(publisherRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(uuid).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponse> updatePublisher(@PathVariable UUID id,
                                                             @RequestBody PublisherRequest publisherRequest) {
        publisherService.update(id, publisherRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PublisherResponse> deletePublisher(@PathVariable UUID id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
