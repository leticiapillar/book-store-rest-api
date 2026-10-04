package com.leticiapillar.books.requests;

import java.util.Set;

public record BookRequest(String title, String isbn, Set<BookAuthorRequest> authors, BookPublisherRequest publisher) {
}
