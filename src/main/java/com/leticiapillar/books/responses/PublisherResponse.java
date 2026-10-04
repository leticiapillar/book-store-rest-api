package com.leticiapillar.books.responses;

import java.util.UUID;

public record PublisherResponse(UUID id, String name, Boolean active) {
}
