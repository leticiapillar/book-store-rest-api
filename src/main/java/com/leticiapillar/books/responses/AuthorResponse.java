package com.leticiapillar.books.responses;

import java.util.UUID;

public record AuthorResponse(UUID id, String firstName, String lastName, Boolean active) {
}
