package com.leticiapillar.books.responses;

import java.util.UUID;

public record BookResponse(UUID id, String title, String isbn) {
}
