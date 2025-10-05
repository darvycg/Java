package com.training.database.services;

import com.training.database.domain.BookEntity;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);
}
