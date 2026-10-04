package com.leticiapillar.books.controllers;

import com.leticiapillar.books.responses.PublisherResponse;
import com.leticiapillar.books.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Iterable<PublisherResponse>> getPublishers() {
        return ResponseEntity.ok(publisherService.getPublishers());
    }

}
