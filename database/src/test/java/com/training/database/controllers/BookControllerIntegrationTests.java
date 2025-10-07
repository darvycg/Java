package com.training.database.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.database.TestDataUtil;
import com.training.database.domain.BookEntity;
import com.training.database.domain.dto.BookDto;
import com.training.database.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateUpdateBookSuccessfullyReturnsHttp201Created() throws Exception {
        BookDto testBookDto = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(testBookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testBookDto.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateUpdateBook() throws Exception {
        BookDto testBook = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testBook.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testBook.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsBook() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(testBook.getIsbn(), testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.[0].isbn").value(testBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.[0].title").value(testBook.getTitle())
        );
    }

    @Test
    public void testThatGetBooksByIdReturnsBook() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookA(null);
        bookService.createUpdateBook(testBook.getIsbn(), testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + testBook.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testBook.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(testBook.getAuthorEntity())
        );
    }

    @Test
    public void testThatGetAuthorByIdReturns404WhenAuthorDoesNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-1-2345-6789-1")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatUpdateBookReturns200Ok() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookA(null);
        BookEntity updatedBook = bookService.createUpdateBook(testBook.getIsbn(), testBook);
        updatedBook.setTitle("I messed up, so sorry!");
        String savedTestBook = objectMapper.writeValueAsString(updatedBook);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testBook.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(savedTestBook)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(updatedBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(updatedBook.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(updatedBook.getAuthorEntity())
        );
    }

    @Test
    public void testThatPartialUpdateBookReturns200AndBook() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookA(null);
        BookEntity updatedBook = bookService.createUpdateBook(testBook.getIsbn(), testBook);
        updatedBook.setTitle("I messed up, so sorry!");
        String savedTestBook = objectMapper.writeValueAsString(updatedBook);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + testBook.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(savedTestBook)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(updatedBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(updatedBook.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(updatedBook.getAuthorEntity())
        );
    }

    @Test
    public void testThatPartialUpdateOnNonExistentBookReturns404() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookA(null);
        String savedTestBook = objectMapper.writeValueAsString(testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + testBook.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(savedTestBook)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatExistingDeleteBookReturns204() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookA(null);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/" + testBook.getIsbn())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))

        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatNonExistentDeleteBookReturns204() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/1234567890")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))

        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}
